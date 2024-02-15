package com.stackroute.faq.service;

import com.stackroute.faq.exception.FaqAlreadyExistsException;
import com.stackroute.faq.exception.FaqNotFoundException;
import com.stackroute.faq.model.Faq;
import com.stackroute.faq.repository.FaqRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FaqServiceTest {

    private Faq faqOne;
    private Faq faqTwo;
    private Optional<Faq> faqOptional;

    @Mock
    private FaqRepository faqRepository;

    @InjectMocks
    private FaqService faqService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.faqOne = new Faq();
        this.faqOne.setQ("What are product offered?");
        this.faqOne.setA("1) Cards  2) Accounts");
        this.faqOne.setId("998");

        this.faqOptional = Optional.of(faqOne);

        this.faqTwo = new Faq();
        this.faqTwo.setQ("What are loans offered?");
        this.faqTwo.setA("1) Study  2) Vehical");
        this.faqTwo.setId("999");
    }

    @Test
    public void createSuccess() throws FaqAlreadyExistsException {
        when(faqRepository.save(this.faqOne)).thenReturn(this.faqOne);
        Faq fetchedFaq = this.faqService.addFaq(this.faqOne);
        Assert.assertEquals(this.faqOne, fetchedFaq);
    }

    @Test
    public void deleteSuccess() throws FaqNotFoundException {
        when(faqRepository.findById(this.faqOne.getId())).thenReturn(this.faqOptional);
        boolean fetchedStatus = this.faqService.deleteFaq(this.faqOne.getId());
        Assert.assertEquals(true, fetchedStatus);
    }

    @Test(expected = FaqNotFoundException.class)
    public void deleteFailure() throws FaqNotFoundException {
        when(faqRepository.findById(this.faqOne.getId())).thenReturn(Optional.empty());
        boolean fetchedStatus = this.faqService.deleteFaq(this.faqOne.getId());
        Assert.assertEquals(false, fetchedStatus);
    }

}
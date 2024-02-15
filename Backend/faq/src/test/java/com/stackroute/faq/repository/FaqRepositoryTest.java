package com.stackroute.faq.repository;

import com.stackroute.faq.model.Faq;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class FaqRepositoryTest {

    @Autowired
    private FaqRepository faqRepository;
    private Faq faqOne;
    private Faq faqTwo;
    @Before
    public void setUp(){

        this.faqOne = new Faq();
        this.faqOne.setQ("What are product offered?");
        this.faqOne.setA("1) Cards  2) Accounts");
        this.faqOne.setId("998");


        this.faqTwo = new Faq();
        this.faqTwo.setQ("What are loans offered?");
        this.faqTwo.setA("1) Study  2) Vehical");
        this.faqTwo.setId("999");

    }

    @After
    public void tearDown(){
        this.faqRepository.deleteAll();
    }

    @Test
    public void saveSuccess(){
        this.faqRepository.save(this.faqOne);
        Faq fetchFaq = this.faqRepository.findById(this.faqOne.getId()).get();
        Assert.assertEquals("998", fetchFaq.getId());
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteSuccess(){
        this.faqRepository.save(this.faqOne);
        this.faqRepository.save(this.faqTwo);
        Faq fetchFaq = this.faqRepository.findById(this.faqOne.getId()).get();
        Assert.assertEquals("998", fetchFaq.getId());
        this.faqRepository.deleteById(this.faqOne.getId());
        Faq fetchFaqAgain = this.faqRepository.findById(this.faqOne.getId()).get();
    }

}
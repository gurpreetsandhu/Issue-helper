package com.stackroute.faq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.faq.exception.FaqAlreadyExistsException;
import com.stackroute.faq.exception.FaqNotFoundException;
import com.stackroute.faq.model.Faq;
import com.stackroute.faq.service.FaqService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FaqControllerTest {

    private Faq faqOne;
    private Faq faqTwo;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FaqService faqService;

    @InjectMocks
    private FaqController faqController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.faqController).build();
        this.faqOne = new Faq();
        this.faqOne.setQ("What are product offered?");
        this.faqOne.setA("1) Cards  2) Accounts");
        this.faqOne.setId("998");


        this.faqTwo = new Faq();
        this.faqTwo.setQ("What are loans offered?");
        this.faqTwo.setA("1) Study  2) Vehical");
        this.faqTwo.setId("999");
    }


    @Test
    public void createSuccess() throws Exception {
        when(faqService.addFaq(any())).thenReturn(this.faqOne);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/faq")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.faqOne)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createFailure() throws Exception {
        when(faqService.addFaq(any())).thenThrow(FaqAlreadyExistsException.class);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/faq")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.faqOne)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteSuccess() throws Exception {
        when(faqService.deleteFaq(this.faqOne.getId())).thenReturn(true);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/" + this.faqOne.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteFailure() throws Exception {
        when(faqService.deleteFaq(this.faqOne.getId())).thenThrow(FaqNotFoundException.class);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/" + this.faqOne.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
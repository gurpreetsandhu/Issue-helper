package com.stackroute.query.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.query.exception.QueryAlreadyExistsException;
import com.stackroute.query.exception.QueryNotFoundException;
import com.stackroute.query.model.Query;
import com.stackroute.query.service.QueryService;
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
public class QueryControllerTest {

    private Query queryOne;
    private Query queryTwo;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QueryService queryService;

    @InjectMocks
    private QueryController queryController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.queryController).build();
        this.queryOne = new Query();
        this.queryOne.setQ("What are product offered?");
        this.queryOne.setA("1) Cards  2) Accounts");
        this.queryOne.setId("111");
        this.queryOne.setLink("www.natwest.com/product");


        this.queryTwo = new Query();
        this.queryTwo.setQ("What are loans offered?");
        this.queryTwo.setA("1) Study  2) Vehical");
        this.queryTwo.setId("222");
        this.queryTwo.setLink("www.natwest.com/loans");
    }

    @Test
    public void createSuccess() throws Exception {
        when(queryService.addQuery(any())).thenReturn(this.queryOne);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/query")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.queryOne)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createFailure() throws Exception {
        when(queryService.addQuery(any())).thenThrow(QueryAlreadyExistsException.class);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/query")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.queryOne)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteSuccess() throws Exception {
        when(queryService.deleteQuery(this.queryOne.getId())).thenReturn(true);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/" + this.queryOne.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteFailure() throws Exception {
        when(queryService.deleteQuery(this.queryOne.getId())).thenThrow(QueryNotFoundException.class);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/" + this.queryOne.getId())
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
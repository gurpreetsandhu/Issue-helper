package com.stackroute.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.account.exception.AccountAlreadyExistsException;
import com.stackroute.account.exception.AccountNotFoundException;
import com.stackroute.account.model.Account;
import com.stackroute.account.model.Transaction;
import com.stackroute.account.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AccountControllerTest {

    private Account account;
    private Transaction transaction;
    private List<Transaction> transactionList;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.accountController).build();
        this.transaction = new Transaction();
        this.transaction.setAmount(800);
        this.transaction.setDate("21-09-2019");
        this.transaction.setId("123");
        this.transaction.setTime("20:43:42");
        this.transaction.setTo_from("100009000");
        this.transaction.setType("Credit");
        this.transaction.setUserId("780090");

        this.transactionList = new ArrayList<>();
        this.transactionList.add(transaction);

        this.account = new Account();
        this.account.setAccount_holder("Charles");
        this.account.setAccount_number("100009009");
        this.account.setAccount_type("Saving");
        this.account.setBalance(7000);
        this.account.setBranch("Main Road");
        this.account.setId("789");
        this.account.setIfsc_code("NAW45678");
        this.account.setLoan_status("No");
        this.account.setTransacation_summary(transactionList);
    }

    @Test
    public void createSuccess() throws Exception {
        when(accountService.addAccount(any())).thenReturn(this.account);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/account")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.account)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createFailure() throws Exception {
        when(accountService.addAccount(any())).thenThrow(AccountAlreadyExistsException.class);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/account")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.account)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteSuccess() throws Exception {
        when(accountService.deleteAccount(this.account.getId())).thenReturn(true);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/" + this.account.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteFailure() throws Exception {
        when(accountService.deleteAccount(this.account.getId())).thenThrow(AccountNotFoundException.class);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/" + this.account.getId())
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
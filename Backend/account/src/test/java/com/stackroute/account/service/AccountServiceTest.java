package com.stackroute.account.service;

import com.stackroute.account.exception.AccountAlreadyExistsException;
import com.stackroute.account.exception.AccountNotFoundException;
import com.stackroute.account.model.Account;
import com.stackroute.account.model.Transaction;
import com.stackroute.account.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    private Account account;
    private Transaction transaction;
    private List<Transaction> transactionList;
    private Optional<Account> accountOptional;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
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

        this.accountOptional = Optional.of(account);
    }

    @Test
    public void createSuccess() throws AccountAlreadyExistsException {
        when(accountRepository.save(this.account)).thenReturn(this.account);
        Account fetchedAccount = this.accountService.addAccount(this.account);
        Assert.assertEquals(this.account, fetchedAccount);
    }

    @Test
    public void deleteSuccess() throws AccountNotFoundException {
        when(accountRepository.findById(this.account.getId())).thenReturn(this.accountOptional);
        boolean fetchedStatus = this.accountService.deleteAccount(this.account.getId());
        Assert.assertEquals(true, fetchedStatus);
    }

    @Test(expected = AccountNotFoundException.class)
    public void deleteFailure() throws AccountNotFoundException {
        when(accountRepository.findById(this.account.getId())).thenReturn(Optional.empty());
        boolean fetchedStatus = this.accountService.deleteAccount(this.account.getId());
        Assert.assertEquals(false, fetchedStatus);
    }

}
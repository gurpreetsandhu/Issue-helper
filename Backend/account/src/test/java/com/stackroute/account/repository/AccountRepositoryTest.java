package com.stackroute.account.repository;

import com.stackroute.account.model.Account;
import com.stackroute.account.model.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;
    private Account account;
    private Transaction transaction;
    private List<Transaction> transactionList;
    @Before
    public void setUp(){
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

    }

    @After
    public void tearDown(){
        this.accountRepository.deleteAll();
    }

    @Test
    public void saveSuccess(){
        this.accountRepository.save(this.account);
        Account fetchAccount = this.accountRepository.findById(this.account.getId()).get();
        Assert.assertEquals("789", fetchAccount.getId());
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteSuccess(){
        this.accountRepository.save(this.account);
        Account fetchAccount = this.accountRepository.findById(this.account.getId()).get();
        Assert.assertEquals("789", fetchAccount.getId());
        this.accountRepository.deleteById(this.account.getId());
        Account fetchAccountAgain = this.accountRepository.findById(this.account.getId()).get();
    }

}
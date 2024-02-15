package com.stackroute.account.service;



import com.stackroute.account.exception.AccountAlreadyExistsException;
import com.stackroute.account.exception.AccountNotFoundException;
import com.stackroute.account.model.Account;
import com.stackroute.account.model.Transaction;

import java.util.List;

public interface AccountServiceInterface {

    List<Account> getAllAccount() throws AccountNotFoundException;
    Account addAccount(Account account) throws AccountAlreadyExistsException;
    Account getAccount(String accountNumber) throws AccountNotFoundException;
    boolean deleteAccount( String id) throws AccountNotFoundException;
    boolean deleteAll();
    boolean addTransaction(Transaction transaction) throws AccountNotFoundException;
}

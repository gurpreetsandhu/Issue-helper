package com.stackroute.account.service;

import com.stackroute.account.exception.AccountAlreadyExistsException;
import com.stackroute.account.exception.AccountNotFoundException;
import com.stackroute.account.model.Account;
import com.stackroute.account.model.Transaction;
import com.stackroute.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements AccountServiceInterface {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAllAccount() throws AccountNotFoundException {
        List<Account> accountObjectList = this.accountRepository.findAll();
        if(accountObjectList == null || accountObjectList.isEmpty()){
            throw new AccountNotFoundException("Account Not Found");
        }else{
            return accountObjectList;
        }
    }

    @Override
    public Account addAccount(Account account) throws AccountAlreadyExistsException {
        return this.accountRepository.save(account);
    }

    @Override
    public Account getAccount(String accountNumber) throws AccountNotFoundException {
        List<Account> accountObject = this.accountRepository.findAll();
        Account accountToReturn = null;
        for(Account account:accountObject){
            if(account.getAccount_number().equals(accountNumber)){
                accountToReturn = account;
                break;
            }
        }
        if(accountObject == null){
            throw new AccountNotFoundException("Account Not Found");
        }
        return accountToReturn;
    }

    @Override
    public boolean deleteAccount(String id) throws AccountNotFoundException {
        Optional<Account> accountObjectOptional = this.accountRepository.findById(id);
        if(!accountObjectOptional.isPresent()){
            throw new AccountNotFoundException("Account Not Found");
        }
        this.accountRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        this.accountRepository.deleteAll();
        return true;
    }

    @Override
    public boolean addTransaction(Transaction transaction) throws AccountNotFoundException {
        Optional<Account> accountObjectOptional = this.accountRepository.findById(transaction.getUserId());
        if(!accountObjectOptional.isPresent()){
            throw new AccountNotFoundException("Account Not Found");
        }
        Account accountFetched = accountObjectOptional.get();
        List<Transaction> allTransactions = accountFetched.getTransacation_summary();
        if(allTransactions == null){
            allTransactions = new ArrayList<>();
        }
        allTransactions.add(transaction);
        accountFetched.setTransacation_summary(allTransactions);
        this.accountRepository.deleteById(transaction.getUserId());
        this.accountRepository.save(accountFetched);
        return true;
    }
}

package com.stackroute.account.controller;

import com.stackroute.account.exception.AccountNotFoundException;
import com.stackroute.account.model.Account;
import com.stackroute.account.model.Transaction;
import com.stackroute.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@CrossOrigin
@EnableFeignClients
@RibbonClient(name = "accountcontroller")
public class AccountController {

    private AccountService accountService;
    private ResponseEntity responseEntity;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/accountAll")
    public ResponseEntity getAccount(){
        try{
            List<Account> accountObjectList = this.accountService.getAllAccount();
            this.responseEntity = new ResponseEntity(accountObjectList, HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return this.responseEntity;
    }

    @GetMapping("/account")
    public ResponseEntity getAccountByNumber(@RequestParam("accountNumber") String accountNumber){
        try{
            Account accountObjectList = this.accountService.getAccount(accountNumber);
            this.responseEntity = new ResponseEntity(accountObjectList, HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return this.responseEntity;
    }

    @PostMapping("/account")
    public ResponseEntity addAccount(@RequestBody Account account){
        try{
            Account returnStatus = this.accountService.addAccount(account);
            if(returnStatus != null){
                this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.CREATED);
            }else{
                this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.CONFLICT);
            }
        }catch(Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }

        return this.responseEntity;
    }

    @PostMapping("/account/transaction")
    public ResponseEntity addTransaction(@RequestBody Transaction transaction){
        try{
            boolean returnStatus = this.accountService.addTransaction(transaction);
            this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.CREATED);
        }catch(Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }

        return this.responseEntity;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAccount(@PathVariable("id") String id){
        try{
            boolean returnStatus = this.accountService.deleteAccount(id);
            if(returnStatus) {
                this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.OK);
            }else{
                this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.NOT_FOUND);
            }
        } catch (AccountNotFoundException e) {
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return this.responseEntity;
    }


    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteAll(){
        boolean returnStatus = this.accountService.deleteAll();
        this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.OK);
        return this.responseEntity;
    }
}

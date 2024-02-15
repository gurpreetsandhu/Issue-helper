package com.stackroute.faq.controller;

import com.stackroute.faq.exception.FaqNotFoundException;
import com.stackroute.faq.model.Faq;
import com.stackroute.faq.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@EnableFeignClients
@RibbonClient(name = "faqcontroller")
public class FaqController {

    private FaqService faqService;
    private ResponseEntity responseEntity;

    @Autowired
    public FaqController(FaqService faqService){
        this.faqService = faqService;
    }

    @GetMapping("/faq")
    public ResponseEntity getFaq(){
        try{
            List<Faq> faqObjectList = this.faqService.getAllFaq();
            this.responseEntity = new ResponseEntity(faqObjectList, HttpStatus.OK);
        } catch (FaqNotFoundException e) {
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return this.responseEntity;
    }

    @PostMapping("/faq")
    public ResponseEntity addFaq(@RequestBody Faq faq){
        try{
            Faq returnStatus = this.faqService.addFaq(faq);
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFaq(@PathVariable("id") String id){
        try{
            boolean returnStatus = this.faqService.deleteFaq(id);
            if(returnStatus) {
                this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.OK);
            }else{
                this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.NOT_FOUND);
            }
        } catch (FaqNotFoundException e) {
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return this.responseEntity;
    }


    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteAllFaq(){
        boolean returnStatus = this.faqService.deleteAll();
        this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.OK);
        return this.responseEntity;
    }
}

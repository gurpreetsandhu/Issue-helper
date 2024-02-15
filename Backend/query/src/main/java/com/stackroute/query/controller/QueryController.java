package com.stackroute.query.controller;

import com.stackroute.query.exception.QueryNotFoundException;
import com.stackroute.query.model.Query;
import com.stackroute.query.service.QueryService;
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
@RibbonClient(name = "querycontroller")
public class QueryController {

    private QueryService queryService;
    private ResponseEntity responseEntity;

    @Autowired
    public QueryController(QueryService queryService){
        this.queryService = queryService;
    }

    @GetMapping("/query")
    public ResponseEntity getQuery(){
        try{
            List<Query> queryObjectList = this.queryService.getAllQuery();
            this.responseEntity = new ResponseEntity(queryObjectList, HttpStatus.OK);
        } catch (QueryNotFoundException e) {
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return this.responseEntity;
    }

    @PostMapping("/query")
    public ResponseEntity addQuery(@RequestBody Query query){
        try{
            Query returnStatus = this.queryService.addQuery(query);
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
    public ResponseEntity deleteQuery(@PathVariable("id") String id){
        try{
            boolean returnStatus = this.queryService.deleteQuery(id);
            if(returnStatus) {
                this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.OK);
            }else{
                this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.NOT_FOUND);
            }
        } catch (QueryNotFoundException e) {
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return this.responseEntity;
    }


    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteAllFaq(){
        boolean returnStatus = this.queryService.deleteAll();
        this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.OK);
        return this.responseEntity;
    }

    @PostMapping("/query/search")
    public ResponseEntity querySearch(@RequestBody Query query){
        try{
            String sentence = query.getQ();
            List<Query> resultantQueries = this.queryService.searchQuery(sentence);
            this.responseEntity = new ResponseEntity(resultantQueries, HttpStatus.CREATED);
        } catch (QueryNotFoundException e) {
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }catch (Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }

        return this.responseEntity;
    }
}

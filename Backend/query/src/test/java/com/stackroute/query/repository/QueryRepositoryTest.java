package com.stackroute.query.repository;

import com.stackroute.query.model.Query;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class QueryRepositoryTest {

    @Autowired
    private QueryRepository queryRepository;
    private Query queryOne;
    private Query queryTwo;
    @Before
    public void setUp(){
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

    @After
    public void tearDown(){
        this.queryRepository.deleteAll();
    }

    @Test
    public void saveSuccess(){
        this.queryRepository.save(this.queryOne);
        Query fetchQuery = this.queryRepository.findById(this.queryOne.getId()).get();
        Assert.assertEquals("111", fetchQuery.getId());
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteSuccess(){
        this.queryRepository.save(this.queryOne);
        this.queryRepository.save(this.queryTwo);
        Query fetchQuery = this.queryRepository.findById(this.queryOne.getId()).get();
        Assert.assertEquals("111", fetchQuery.getId());
        this.queryRepository.deleteById(this.queryOne.getId());
        Query fetchQueryAgain = this.queryRepository.findById(this.queryOne.getId()).get();
    }

}
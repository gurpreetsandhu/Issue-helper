package com.stackroute.query.service;

import com.stackroute.query.exception.QueryAlreadyExistsException;
import com.stackroute.query.exception.QueryNotFoundException;
import com.stackroute.query.model.Query;
import com.stackroute.query.repository.QueryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class QueryServiceTest {

    private Query queryOne;
    private Query queryTwo;
    private Optional<Query> queryOptional;

    @Mock
    private QueryRepository queryRepository;

    @InjectMocks
    private QueryService queryService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.queryOne = new Query();
        this.queryOne.setQ("What are product offered?");
        this.queryOne.setA("1) Cards  2) Accounts");
        this.queryOne.setId("111");
        this.queryOne.setLink("www.natwest.com/product");

        this.queryOptional = Optional.of(this.queryOne);


        this.queryTwo = new Query();
        this.queryTwo.setQ("What are loans offered?");
        this.queryTwo.setA("1) Study  2) Vehical");
        this.queryTwo.setId("222");
        this.queryTwo.setLink("www.natwest.com/loans");
    }

    @Test
    public void createSuccess() throws QueryAlreadyExistsException {
        when(queryRepository.save(this.queryOne)).thenReturn(this.queryOne);
        Query fetchedQuery = this.queryService.addQuery(this.queryOne);
        Assert.assertEquals(this.queryOne, fetchedQuery);
    }

    @Test
    public void deleteSuccess() throws QueryNotFoundException {
        when(queryRepository.findById(this.queryOne.getId())).thenReturn(this.queryOptional);
        boolean fetchedStatus = this.queryService.deleteQuery(this.queryOne.getId());
        Assert.assertEquals(true, fetchedStatus);
    }

    @Test(expected = QueryNotFoundException.class)
    public void deleteFailure() throws QueryNotFoundException {
        when(queryRepository.findById(this.queryOne.getId())).thenReturn(Optional.empty());
        boolean fetchedStatus = this.queryService.deleteQuery(this.queryOne.getId());
        Assert.assertEquals(false, fetchedStatus);
    }

}
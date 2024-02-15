package com.stackroute.query.service;


import com.stackroute.query.exception.QueryAlreadyExistsException;
import com.stackroute.query.exception.QueryNotFoundException;
import com.stackroute.query.model.Query;

import java.util.List;

public interface QueryServiceInterface {

    List<Query> getAllQuery() throws QueryNotFoundException;
    Query addQuery(Query query) throws QueryAlreadyExistsException;
    boolean deleteQuery( String id) throws QueryNotFoundException;
    boolean deleteAll();
    List<Query> searchQuery(String sentence) throws QueryNotFoundException;
}

package com.stackroute.query.service;

import com.stackroute.query.exception.QueryAlreadyExistsException;
import com.stackroute.query.exception.QueryNotFoundException;
import com.stackroute.query.model.Query;
import com.stackroute.query.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QueryService implements QueryServiceInterface {

    private QueryRepository queryRepository;

    @Autowired
    public QueryService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @Override
    public List<Query> getAllQuery() throws QueryNotFoundException {
        List<Query> faqObjectList = this.queryRepository.findAll();
        if (faqObjectList == null || faqObjectList.isEmpty()) {
            throw new QueryNotFoundException("Query Not Found");
        } else {
            return faqObjectList;
        }
    }

    @Override
    public Query addQuery(Query query) throws QueryAlreadyExistsException {
        return this.queryRepository.save(query);
    }

    @Override
    public boolean deleteQuery(String id) throws QueryNotFoundException {
        Optional<Query> queryObjectOptional = this.queryRepository.findById(id);
        if (!queryObjectOptional.isPresent()) {
            throw new QueryNotFoundException("Query Not Found");
        }
        this.queryRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        this.queryRepository.deleteAll();
        return true;
    }

    @Override
    public List<Query> searchQuery(String sentence) throws QueryNotFoundException {
        List<Query> allQueryList = this.queryRepository.findAll();
        if (allQueryList == null || allQueryList.isEmpty()) {
            throw new QueryNotFoundException("Query Not Found");
        } else {
            List<Query> resultantQuery = searchForResult(allQueryList, sentence);
            if (resultantQuery == null || resultantQuery.isEmpty()) {
                throw new QueryNotFoundException("No Resultant Query");
            }
            return resultantQuery;
        }
    }

    private List<Query> searchForResult(List<Query> allQueryList, String sentence) {
        String split[] = null;
        if (sentence.length() == 1) {
            split[0] = sentence;
        } else {
            split = sentence.split("\\s+");
        }

        ArrayList<Query> bestList = new ArrayList<>();
        ArrayList<Query> okList = new ArrayList<>();
        for (int i = 0; i < allQueryList.size(); i++) {
            int count = 0;
            String url = allQueryList.get(i).getQ();
            for (int j = 0; j < split.length; j++) {
                if (url.toLowerCase().contains(split[j].toLowerCase())) {

                    count++;
                }
            }
            if (count > 1) {
                bestList.add(allQueryList.get(i));
            }
            if (count >= 1) {
                okList.add(allQueryList.get(i));
            }
        }
        if (bestList.isEmpty())
            return okList;
        else
            return bestList;
    }

}

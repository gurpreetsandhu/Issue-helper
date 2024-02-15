package com.stackroute.faq.service;



import com.stackroute.faq.exception.FaqAlreadyExistsException;
import com.stackroute.faq.exception.FaqNotFoundException;
import com.stackroute.faq.model.Faq;
import java.util.List;

public interface FaqServiceInterface {

    List<Faq> getAllFaq() throws FaqNotFoundException;
    Faq addFaq(Faq faq) throws FaqAlreadyExistsException;
    boolean deleteFaq( String id) throws FaqNotFoundException;
    boolean deleteAll();
}

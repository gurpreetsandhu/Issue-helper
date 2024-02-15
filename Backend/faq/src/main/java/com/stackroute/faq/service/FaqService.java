package com.stackroute.faq.service;

import com.stackroute.faq.exception.FaqAlreadyExistsException;
import com.stackroute.faq.exception.FaqNotFoundException;
import com.stackroute.faq.model.Faq;
import com.stackroute.faq.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaqService implements FaqServiceInterface {

    private FaqRepository faqRepository;

    @Autowired
    public FaqService(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Override
    public List<Faq> getAllFaq() throws FaqNotFoundException {
        List<Faq> faqObjectList = this.faqRepository.findAll();
        if(faqObjectList == null || faqObjectList.isEmpty()){
            throw new FaqNotFoundException("Faq Not Found");
        }else{
            return faqObjectList;
        }
    }

    @Override
    public Faq addFaq(Faq faq) throws FaqAlreadyExistsException {
        return this.faqRepository.save(faq);
    }

    @Override
    public boolean deleteFaq(String id) throws FaqNotFoundException {
        Optional<Faq> faqObjectOptional = this.faqRepository.findById(id);
        if(!faqObjectOptional.isPresent()){
            throw new FaqNotFoundException("Faq Not Found");
        }
        this.faqRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        this.faqRepository.deleteAll();
        return true;
    }
}

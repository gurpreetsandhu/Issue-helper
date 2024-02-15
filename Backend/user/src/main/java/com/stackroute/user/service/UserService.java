package com.stackroute.user.service;

import com.stackroute.user.exception.UserAlreadyExistsException;
import com.stackroute.user.exception.UserNotFound;
import com.stackroute.user.model.User;
import com.stackroute.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface{

    private UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public User checkUser(String customerId, String password) throws UserNotFound {
        User user = this.userRepository.findByCustomerIdAndPassword(customerId, password);
        if(user == null){
            throw new UserNotFound("Username or Password is Wrong");
        }else{
            return user;
        }
    }

    @Override
    public boolean saveUser(User user) throws UserAlreadyExistsException {
        User returnUser = this.userRepository.findByCustomerIdAndPassword(user.getCustomerId(), user.getPassword());
        if(returnUser != null){
            throw new UserAlreadyExistsException("User Already Exists");
        }else{
            this.userRepository.save(user);
            return true;
        }
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User getAccountNumber(String customerId) throws UserNotFound {
        User user = this.userRepository.findByCustomerId(customerId);
        if(user == null){
            throw new UserNotFound("User Not Found");
        }
        return user;
    }
}

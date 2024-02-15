package com.stackroute.user.service;

import com.stackroute.user.exception.UserAlreadyExistsException;
import com.stackroute.user.exception.UserNotFound;
import com.stackroute.user.model.User;

import java.util.List;

public interface UserServiceInterface {

    User checkUser(String username, String password) throws UserNotFound;
    boolean saveUser(User user) throws UserAlreadyExistsException;
    List<User> getAll();
    User getAccountNumber(String customerId) throws UserNotFound;
}

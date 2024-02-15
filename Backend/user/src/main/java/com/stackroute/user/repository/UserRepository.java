package com.stackroute.user.repository;

import com.stackroute.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByCustomerIdAndPassword(String username, String password);
    User findByCustomerId(String customerId);
}

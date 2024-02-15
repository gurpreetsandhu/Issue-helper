package com.stackroute.user.controller;

import com.stackroute.user.exception.UserAlreadyExistsException;
import com.stackroute.user.exception.UserNotFound;
import com.stackroute.user.exception.UserNullException;
import com.stackroute.user.model.User;
import com.stackroute.user.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@EnableFeignClients
@RibbonClient(name = "usercontroller")
public class UserController {

    private UserService userService;
    private ResponseEntity responseEntity;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity saveUser(@RequestBody User user){
        try{
            boolean returnStatus = this.userService.saveUser(user);
            if(returnStatus){
                this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.CREATED);
            }else{
                this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.CONFLICT);
            }
        } catch (UserAlreadyExistsException e) {
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }catch(Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }

        return this.responseEntity;
    }

    @GetMapping("/user")
    public ResponseEntity login(){
        try {
            List<User> returnUser = this.userService.getAll();
            this.responseEntity = new ResponseEntity(returnUser, HttpStatus.OK);
        } catch (Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return this.responseEntity;
    }

    @PostMapping("/user/getToken")
    public ResponseEntity getTheToken(@RequestBody User user){
        Map<String, String> map = new HashMap<>();
        try {
            String jwtToken = getToken(user.getCustomerId(), user.getPassword());
            map.put("token", jwtToken);
            map.put("message", "OK");
            this.responseEntity = new ResponseEntity(map, HttpStatus.OK);
        } catch (Exception e) {
            map.put("token", null);
            map.put("message", e.getMessage());
            this.responseEntity = new ResponseEntity(map, HttpStatus.NOT_FOUND);
        }
        return this.responseEntity;
    }

    @PostMapping("/user/getAccount")
    public ResponseEntity getTheAccount(@RequestBody User user){
        try{
            User returnedUser = this.userService.getAccountNumber(user.getCustomerId());
            this.responseEntity = new ResponseEntity(returnedUser, HttpStatus.CREATED);
        } catch (UserNotFound userNotFound) {
            this.responseEntity = new ResponseEntity(userNotFound.getMessage(), HttpStatus.CONFLICT);
        }catch( Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }

        return this.responseEntity;
    }


    public String getToken(String username, String password) throws Exception {
        if(username == null || password == null){
            throw new UserNullException("User Credentials are Null");
        }
        else{
            String jwtToken = Jwts.builder().setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 500000))
                    .signWith(SignatureAlgorithm.HS256, "SecretKey")
                    .compact();
            return jwtToken;
        }
    }
}

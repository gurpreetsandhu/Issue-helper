package com.stackroute.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.user.exception.UserAlreadyExistsException;
import com.stackroute.user.model.User;
import com.stackroute.user.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

    private User userOne;
    private User userTwo;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
        this.userOne = new User();
        this.userOne.setAccount("100100100");
        this.userOne.setAddress("QWE RTY UIO");
        this.userOne.setContact("9000090000");
        this.userOne.setCustomerId("101");
        this.userOne.setFirstName("ABCDEF");
        this.userOne.setId(100001);
        this.userOne.setLastName("JKLMN");
        this.userOne.setPassword("qwerty");

        this.userTwo = new User();
        this.userTwo.setAccount("200200200");
        this.userTwo.setAddress("CVB NML ABC");
        this.userTwo.setContact("9999999999");
        this.userTwo.setCustomerId("202");
        this.userTwo.setFirstName("ASDF");
        this.userTwo.setId(200002);
        this.userTwo.setLastName("DFGH");
        this.userTwo.setPassword("uiop");
    }

    @Test
    public void createSuccess() throws Exception {
        when(userService.saveUser(any())).thenReturn(true);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.userOne)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createFailure() throws Exception {
        when(userService.saveUser(any())).thenThrow(UserAlreadyExistsException.class);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.userOne)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
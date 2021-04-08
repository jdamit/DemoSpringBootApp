package com.reactive.world.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactive.world.model.UserDto;
import com.reactive.world.serviceImpl.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.List;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserServiceImpl userService;

    private List<UserDto> users;

    private UserDto user;

    private String URI = "/users";

    @BeforeEach
    void setUp(){
        users = List.of(new UserDto("Amit", "Kushwaha", "jdamit2027@gmail.com", "sector 120"),
                new UserDto("Amit", "Kushwaha", "jdamit2027@gmail.com", "sector 120"),
                new UserDto("Amit", "Kushwaha", "jdamit2027@gmail.com", "sector 120"));
        user = new UserDto("Rahul", "Swagger", "rahul.swagger@gmail.com", "sector 120");
    }

    @Test
    //@Disabled
    void getUsersTest() throws Exception {

        Mockito.when(userService.getUsers()).thenReturn(users);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String userJson = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson).isEqualToIgnoringCase(mapper.writeValueAsString(users));
    }

    @Test
    //@Disabled
    void createUserTest() throws Exception {

        Mockito.when(userService.createUser(Mockito.any(UserDto.class))).thenReturn(user);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user).getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String userJson = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson).isNotEmpty();
        Assertions.assertThat(userJson).isEqualToIgnoringCase(mapper.writeValueAsString(user));
    }
}

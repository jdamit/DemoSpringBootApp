package com.reactive.world.controller;

import com.reactive.world.model.UserDto;
import com.reactive.world.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable String id){
        return userService.getUser(id);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto user){
        return userService.createUser(user);
    }
}

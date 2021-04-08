package com.reactive.world.service;

import com.reactive.world.model.UserDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers();

    UserDto getUser(String id);

    UserDto createUser(@RequestBody UserDto user);
}

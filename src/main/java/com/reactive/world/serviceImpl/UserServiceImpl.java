package com.reactive.world.serviceImpl;

import com.reactive.world.model.UserDto;
import com.reactive.world.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private List<UserDto> users = List.of(new UserDto("Amit", "Kushwaha", "jdamit2027@gmail.com", "sector 120")
    ,new UserDto("Amit", "Kushwaha", "jdamit2027@gmail.com", "sector 120"), new UserDto("Amit", "Kushwaha", "jdamit2027@gmail.com", "sector 120"));
    @Override
    public List<UserDto> getUsers() {
        return users;
    }

    @Override
    public UserDto getUser(String id) {
        return users.get(0);
    }

    @Override
    public UserDto createUser(UserDto user) {
        return users.get(0);
    }
}

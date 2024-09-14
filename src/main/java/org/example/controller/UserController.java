package org.example.controller;

import org.example.dto.request.UserRequestDTO;
import org.example.dto.response.UserResponseDTO;
import org.example.entity.User;
import org.example.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserController {
    private static UserController instance;
    private UserService userService;

    private UserController() {
        userService = new UserService();
    }
    public static UserController getInstance() {
        if(instance == null) {
            instance = new UserController();
        }
        return instance;
    }


    public Optional<UserResponseDTO> save(UserRequestDTO userRequestDto) {
        return userService.save(userRequestDto);
    }

    public void update(UserRequestDTO userRequestDto) {
        userService.update(userRequestDto);
    }
    public void delete(Long silinecekId) {
        userService.delete(silinecekId);
    }

    public List<User> findAll(){
        return userService.findAll();
    }

    public Optional<User> findById(Long id) {
        return userService.findById(id);
    }


    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userService.findByUsernameAndPassword(username, password);
    }

    public boolean isUsernameExist(String username) {
        return userService.isUsernameExist(username);
    }

    public boolean isMailExist(String email) {
        return userService.isMailExist(email);
    }

}

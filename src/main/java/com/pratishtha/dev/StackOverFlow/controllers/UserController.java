package com.pratishtha.dev.StackOverFlow.controllers;

import com.pratishtha.dev.StackOverFlow.dtos.ResponseStatus;
import com.pratishtha.dev.StackOverFlow.dtos.UserDTO.UserLoginRequestDTO;
import com.pratishtha.dev.StackOverFlow.dtos.UserDTO.UserResponseDTO;
import com.pratishtha.dev.StackOverFlow.dtos.UserDTO.UserSignUpRequestDTO;
import com.pratishtha.dev.StackOverFlow.exceptions.UserNotFoundException;
import com.pratishtha.dev.StackOverFlow.model.User;
import com.pratishtha.dev.StackOverFlow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserResponseDTO signUpUser (UserSignUpRequestDTO requestDTO) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        User newUser;

        try {
            newUser = userService.signUser(requestDTO.getName(), requestDTO.getUserName(),
                    requestDTO.getEmail(), requestDTO.getRole());

            responseDTO.setUser(newUser);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (RuntimeException e) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setFailureReason(e.getMessage());
        }
        return responseDTO;
    }

    public UserResponseDTO loginUser (UserLoginRequestDTO requestDTO) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        User member;

        try {
            member = userService.loginUser(requestDTO.getName(), requestDTO.getUserName(),
                    requestDTO.getEmail());

            responseDTO.setUser(member);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (UserNotFoundException e) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setFailureReason(e.getMessage());
        }
        return responseDTO;
    }

}

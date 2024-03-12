package com.pratishtha.dev.StackOverFlow.dtos.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequestDTO {

    private String name;
    private String userName;
    private String email;
    private String role;
}

package com.pratishtha.dev.StackOverFlow.dtos.UserDTO;

import com.pratishtha.dev.StackOverFlow.dtos.ResponseStatus;
import com.pratishtha.dev.StackOverFlow.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private User user;
    private ResponseStatus responseStatus;
    private String failureReason;

}

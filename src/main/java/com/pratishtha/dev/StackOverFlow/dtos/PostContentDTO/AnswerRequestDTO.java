package com.pratishtha.dev.StackOverFlow.dtos.PostContentDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerRequestDTO {

    private String text;
    private List<String> photoUrl;
    private String userEmail;
    private Long questionId;
}

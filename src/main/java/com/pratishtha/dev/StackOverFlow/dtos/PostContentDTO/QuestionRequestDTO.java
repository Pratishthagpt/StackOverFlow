package com.pratishtha.dev.StackOverFlow.dtos.PostContentDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionRequestDTO {

    private String text;
    private List<String> photoUrl;
    private String userEmail;
    private String title;
    private List<String> tags;
}

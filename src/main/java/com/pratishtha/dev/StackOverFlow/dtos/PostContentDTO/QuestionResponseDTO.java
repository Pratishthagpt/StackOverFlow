package com.pratishtha.dev.StackOverFlow.dtos.PostContentDTO;

import com.pratishtha.dev.StackOverFlow.dtos.ResponseStatus;
import com.pratishtha.dev.StackOverFlow.model.Question;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResponseDTO {

    private Question question;
    private ResponseStatus responseStatus;
    private String failureReason;
}

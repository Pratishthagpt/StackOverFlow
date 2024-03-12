package com.pratishtha.dev.StackOverFlow.dtos.PostContentDTO;

import com.pratishtha.dev.StackOverFlow.dtos.ResponseStatus;
import com.pratishtha.dev.StackOverFlow.model.Answer;
import com.pratishtha.dev.StackOverFlow.model.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDTO {

    private Comment comment;
    private ResponseStatus responseStatus;
    private String failureReason;
}

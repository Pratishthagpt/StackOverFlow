package com.pratishtha.dev.StackOverFlow.controllers;

import com.pratishtha.dev.StackOverFlow.dtos.*;
import com.pratishtha.dev.StackOverFlow.dtos.PostContentDTO.*;
import com.pratishtha.dev.StackOverFlow.model.Answer;
import com.pratishtha.dev.StackOverFlow.model.Comment;
import com.pratishtha.dev.StackOverFlow.model.Question;
import com.pratishtha.dev.StackOverFlow.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public QuestionResponseDTO addQuestion (QuestionRequestDTO requestDTO) {
        QuestionResponseDTO responseDTO = new QuestionResponseDTO();
        Question newQuestion;

        try {
            newQuestion = memberService.addQuestion(requestDTO.getText(), requestDTO.getPhotoUrl(),
                    requestDTO.getUserEmail(), requestDTO.getTitle(), requestDTO.getTags());

            responseDTO.setQuestion(newQuestion);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (RuntimeException ex) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setFailureReason(ex.getMessage());
        }
        return responseDTO;
    }

    public AnswerResponseDTO addAnswer (AnswerRequestDTO requestDTO) {
        AnswerResponseDTO responseDTO = new AnswerResponseDTO();
        Answer newAnswer;

        try {
            newAnswer = memberService.addAnswer(requestDTO.getText(), requestDTO.getPhotoUrl(),
                    requestDTO.getUserEmail(), requestDTO.getQuestionId());

            responseDTO.setAnswer(newAnswer);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (RuntimeException ex) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setFailureReason(ex.getMessage());
        }
        return responseDTO;
    }

    public CommentResponseDTO addComment (CommentRequestDTO requestDTO) {
        CommentResponseDTO responseDTO = new CommentResponseDTO();
        Comment newComment;

        try {
            newComment = memberService.addComment(requestDTO.getText(), requestDTO.getPhotoUrl(),
                    requestDTO.getUserEmail(), requestDTO.getQuestionId(), requestDTO.getAnswerId());

            responseDTO.setComment(newComment);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (RuntimeException ex) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setFailureReason(ex.getMessage());
        }
        return responseDTO;
    }

    public void giveBountyToReceiver(BountyDTO bountyDTO) {
        memberService.giveBountyTo(bountyDTO.getMemberId(), bountyDTO.getBountyReputation(),
                bountyDTO.getReceiverId());
    }

    public void upVoteEntity (PostContentDTO postContentDTO) {
        memberService.upVote(postContentDTO.getMemberId(), postContentDTO.getPostContentId(), postContentDTO.getPostContentType());
    }

    public void downVoteEntity (PostContentDTO postContentDTO) {
        memberService.downVote(postContentDTO.getMemberId(), postContentDTO.getPostContentId(), postContentDTO.getPostContentType());
    }

    public void deleteEntity (PostContentDTO postContentDTO) {
        memberService.deleteContent( postContentDTO.getPostContentId(), postContentDTO.getMemberId(), postContentDTO.getPostContentType());
    }

}

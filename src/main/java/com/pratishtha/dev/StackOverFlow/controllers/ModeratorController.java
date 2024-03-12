package com.pratishtha.dev.StackOverFlow.controllers;

import com.pratishtha.dev.StackOverFlow.dtos.PostContentDTO.PostContentDTO;
import com.pratishtha.dev.StackOverFlow.exceptions.ContentNotFoundException;
import com.pratishtha.dev.StackOverFlow.exceptions.InvalidAuthorizationException;
import com.pratishtha.dev.StackOverFlow.exceptions.QuestionNotFoundException;
import com.pratishtha.dev.StackOverFlow.services.MemberService;
import com.pratishtha.dev.StackOverFlow.services.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ModeratorController extends  MemberController{

    private ModeratorService moderatorService;

    @Autowired
    public ModeratorController(MemberService memberService, ModeratorService moderatorService) {
        super(memberService);
        this.moderatorService = moderatorService;
    }

    public void closeQuestion (PostContentDTO postContentDTO) {
        try {
            moderatorService.closeQuestion(postContentDTO.getPostContentId(), postContentDTO.getMemberId());
        }catch (QuestionNotFoundException | InvalidAuthorizationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void closeAnswer (PostContentDTO postContentDTO) {
        try {
            moderatorService.closeAnswer(postContentDTO.getPostContentId(), postContentDTO.getMemberId());
        }catch (ContentNotFoundException | InvalidAuthorizationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void reOpenQuestion (PostContentDTO postContentDTO) {
        try {
            moderatorService.reOpenQuestion(postContentDTO.getPostContentId(), postContentDTO.getMemberId());
        }catch (QuestionNotFoundException | InvalidAuthorizationException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

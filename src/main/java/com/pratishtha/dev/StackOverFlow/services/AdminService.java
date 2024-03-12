package com.pratishtha.dev.StackOverFlow.services;

import com.pratishtha.dev.StackOverFlow.model.AccountStatus;
import com.pratishtha.dev.StackOverFlow.model.User;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends ModeratorService{

    public AdminService(QuestionService questionService, AnswerService answerService, CommentService commentService, UserService userService) {
        super(questionService, answerService, commentService, userService);
    }

    public void blockMember (Long memberIdToBeBlocked, Long memberIdWhoWillBlock) {
        User memberWhoWillBlock = userService.getMember(memberIdWhoWillBlock);
        User memberToBeBlocked = userService.getMember(memberIdToBeBlocked);

        if (memberWhoWillBlock.isAdmin() && !memberToBeBlocked.getAccountStatus().equals(AccountStatus.BLOCKED)) {
            memberToBeBlocked.setAccountStatus(AccountStatus.BLOCKED);
        }
    }

    public void unBlockMember (Long memberIdToBeBlocked, Long memberIdWhoWillBlock) {
        User memberWhoWillBlock = userService.getMember(memberIdWhoWillBlock);
        User memberToBeBlocked = userService.getMember(memberIdToBeBlocked);

        if (memberWhoWillBlock.isAdmin() && !memberToBeBlocked.getAccountStatus().equals(AccountStatus.BLOCKED)) {
            memberToBeBlocked.setAccountStatus(AccountStatus.ACTIVE);
        }
    }

}

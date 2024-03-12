package com.pratishtha.dev.StackOverFlow.services;

import com.pratishtha.dev.StackOverFlow.exceptions.InvalidAuthorizationException;
import com.pratishtha.dev.StackOverFlow.model.Answer;
import com.pratishtha.dev.StackOverFlow.model.PostStatus;
import com.pratishtha.dev.StackOverFlow.model.Question;
import com.pratishtha.dev.StackOverFlow.model.User;
import org.springframework.stereotype.Service;

@Service
public class ModeratorService extends MemberService{

    public ModeratorService(QuestionService questionService, AnswerService answerService, CommentService commentService, UserService userService) {
        super(questionService, answerService, commentService, userService);
    }

    public void closeQuestion (Long questionId, Long memberId) {
        Question question = questionService.getQuestionById(questionId);
        User member = userService.getMember(memberId);

        if (member.isModerator() || member.isAdmin() || member.getId() == question.getCreatedByMember().getId()) {
            question.setQuesStatus(PostStatus.CLOSED);
        }
        else {
            throw new InvalidAuthorizationException("The member is not the moderator and has not rights for this action.");
        }
    }

    public void closeAnswer (Long answerId, Long memberId) {
        Answer answer = answerService.getAnswerById(answerId);
        User member = userService.getMember(memberId);

        if (member.isModerator() || member.isAdmin() || member.getId() == answer.getCreatedByMember().getId()) {
            answer.setAnsStatus(PostStatus.CLOSED);
        }
        else {
            throw new InvalidAuthorizationException("The member is not the moderator and has not rights for this action.");
        }

    }

    public void reOpenQuestion (Long questionId, Long memberId) {
        Question question = questionService.getQuestionById(questionId);
        User member = userService.getMember(memberId);

        if (member.isModerator() || member.isAdmin() || member.getId() == question.getCreatedByMember().getId()) {
            if (question.getQuesStatus().equals(PostStatus.CLOSED)) {
                question.setQuesStatus(PostStatus.OPEN);
            }
        }
        else {
            throw new InvalidAuthorizationException("The member is not the moderator and has not rights for this action.");
        }
    }
}

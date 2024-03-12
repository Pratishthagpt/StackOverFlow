package com.pratishtha.dev.StackOverFlow.services;

import com.pratishtha.dev.StackOverFlow.exceptions.ContentNotFoundException;
import com.pratishtha.dev.StackOverFlow.model.*;
import com.pratishtha.dev.StackOverFlow.repositories.AnswerRepository;
import com.pratishtha.dev.StackOverFlow.repositories.QuestionRepository;
import com.pratishtha.dev.StackOverFlow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MemberService {

    public QuestionService questionService;
    public AnswerService answerService;
    public CommentService commentService;
    public UserService userService;

    @Autowired
    public MemberService(QuestionService questionService, AnswerService answerService,
                         CommentService commentService, UserService userService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.commentService = commentService;
        this.userService = userService;
    }

    public Question addQuestion (String text, List<String> photoUrl, String userEmail, String title,
                                 List<String> tags) {
        return questionService.addQuestion(text, photoUrl, userEmail, title, tags);
    }

    public Answer addAnswer (String text, List<String> photoUrl, String userEmail, Long questionId) {
        return answerService.addAnswer(text, photoUrl, userEmail, questionId);
    }

    public Comment addComment (String text, List<String> photoUrl, String userEmail, Long questionId, Long answerId) {
        return commentService.addComment(text, photoUrl, userEmail, questionId, answerId);
    }

    // a question asker can give bounty to someone who has satisfactorily answered to his/her question
    public void giveBountyTo (Long memberId, int bountyReputation, Long receiverId) {
        User member = userService.getMember(memberId);
        User receiver = userService.getMember(receiverId);

        if (member.getReputation() >= bountyReputation && memberId != receiverId) {
            int updatedMemberRepu = member.getReputation() - bountyReputation;
            member.setReputation(updatedMemberRepu);
            int updatedReceiverRepu = receiver.getReputation() + bountyReputation;
            member.setReputation(updatedReceiverRepu);
        }
    }

    public void upVote (Long memberId, Long postContentId, String postContentType) {
//        get member
        User member = userService.getMember(memberId);
        PostContent content;

//        get content
        if (postContentType.equalsIgnoreCase("Question")) {
            content = questionService.getQuestionById(postContentId);
        }
        else if (postContentType.equalsIgnoreCase("Answer")) {
            content = answerService.getAnswerById(postContentId);
        }
        else if (postContentType.equalsIgnoreCase("Comment")) {
            content = commentService.getCommentById(postContentId);
        }
        else {
            throw new ContentNotFoundException("No content by this Id found");
        }

        Set<Integer> memberWhoUpvotedThisContent = content.getMembersWhoUpvoted();
        Set<Integer> memberWhoDownvotedThisContent = content.getMembersWhoDownvoted();
        int noOfMembersReported = content.getNoOfMemberWhoReported();

        // a member cannot upvote a comment that he/she has already upvoted
        if (!memberWhoUpvotedThisContent.contains(memberId)) {
            // if the member has downvoted this comment in past then upvoting it once just
            // cancels the downvote.
            if (memberWhoDownvotedThisContent.contains(memberId)) {
                memberWhoDownvotedThisContent.remove(memberId);
            }
            else {
                memberWhoUpvotedThisContent.add(Math.toIntExact(memberId));
            }
        }
    }

    public void downVote (Long memberId, Long postContentId, String postContentType) {
//        get member
        User member = userService.getMember(memberId);
        PostContent content;

//        get content
        if (postContentType.equalsIgnoreCase("Question")) {
            content = questionService.getQuestionById(postContentId);
        }
        else if (postContentType.equalsIgnoreCase("Answer")) {
            content = answerService.getAnswerById(postContentId);
        }
        else if (postContentType.equalsIgnoreCase("Comment")) {
            content = commentService.getCommentById(postContentId);
        }
        else {
            throw new ContentNotFoundException("No content by this Id found");
        }

        Set<Integer> memberWhoUpvotedThisContent = content.getMembersWhoUpvoted();
        Set<Integer> memberWhoDownvotedThisContent = content.getMembersWhoDownvoted();
        int noOfMembersReported = content.getNoOfMemberWhoReported();

        // a member cannot downvote a comment that he/she has already downvoted
        if (!memberWhoDownvotedThisContent.contains(memberId)) {
            // if the member has upvoted this comment in past then downvoting it once just
            // cancels the upvote.
            if (memberWhoUpvotedThisContent.contains(memberId)) {
                memberWhoUpvotedThisContent.remove(memberId);
            }
            else {
                memberWhoDownvotedThisContent.add(Math.toIntExact(memberId));
            }
        }
    }

    public void addTagsToQuestion (Long questionId, List<String> tags) {
        Question question = questionService.getQuestionById(questionId);
        List<Tag> tagsList = questionService.getTagsForQuestion(question, tags);
        question.setTags(tagsList);
    }

    public void deleteContent (Long postContentId, Long memberId, String postContentType) {
        User member = userService.getMember(memberId);
        PostContent content;

//        get content
        if (postContentType.equalsIgnoreCase("Question")) {
            content = questionService.getQuestionById(postContentId);
            questionService.deleteQuestionById(postContentId);
        }
        else if (postContentType.equalsIgnoreCase("Answer")) {
            content = answerService.getAnswerById(postContentId);
            answerService.deleteAnswerById(postContentId);
        }
        else if (postContentType.equalsIgnoreCase("Comment")) {
            content = commentService.getCommentById(postContentId);
            commentService.deleteCommentById(postContentId);
        }
        else {
            throw new ContentNotFoundException("No content by this Id found");
        }
    }

}

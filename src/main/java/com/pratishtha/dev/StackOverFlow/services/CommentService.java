package com.pratishtha.dev.StackOverFlow.services;

import com.pratishtha.dev.StackOverFlow.exceptions.UserNotFoundException;
import com.pratishtha.dev.StackOverFlow.model.*;
import com.pratishtha.dev.StackOverFlow.repositories.AnswerRepository;
import com.pratishtha.dev.StackOverFlow.repositories.CommentRepository;
import com.pratishtha.dev.StackOverFlow.repositories.QuestionRepository;
import com.pratishtha.dev.StackOverFlow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private AnswerRepository answerRepository;
    private UserRepository userRepository;
    private QuestionRepository questionRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, AnswerRepository answerRepository, UserRepository userRepository
            , QuestionRepository questionRepository) {
        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public Comment getCommentById (Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isEmpty()) {
            return null;
        }
        return commentOptional.get();
    }

    public Comment addComment(String text, List<String> photoUrl, String userEmail, Long questionId, Long answerId) {

//        find user who is going to ask question and also checking that it should be the member
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User is not member. Please register with valid email.");
        }
        User commentedBy = userOptional.get();

//        add photos
        List<Photo> photos = new ArrayList<>();
        for (String url : photoUrl) {
            Photo photo = new Photo();
            photo.setUrl(url);
            photo.setUser(commentedBy);
            photos.add(photo);
        }

        Comment comment = new Comment();
        comment.setText(text);
        comment.setCommentStatus(PostStatus.OPEN);
        comment.setCreatedByMember(commentedBy);
        comment.setPhotos(photos);
        comment.setMembersWhoDownvoted(new HashSet<>());
        comment.setMembersWhoUpvoted(new HashSet<>());
        comment.setNoOfMemberWhoReported(0);

        Comment savedComment = commentRepository.save(comment);

//        add comment to question
        if (questionRepository.findById(questionId).isPresent()) {
            addCommentToQuestion(questionId, savedComment);
        }

//        add comment to answer
        if (answerRepository.findById(answerId).isPresent()) {
            addCommentToAnswer(answerId, savedComment);
        }

        return savedComment;
    }

    private void addCommentToAnswer(Long answerId, Comment savedComment) {
        Answer answer = answerRepository.findById(answerId).get();
        List<Comment> comments = answer.getAns_comments();
        comments.add(savedComment);
        answer.setAns_comments(comments);
    }

    private void addCommentToQuestion(Long questionId, Comment savedComment) {
        Question question = questionRepository.findById(questionId).get();
        List<Comment> comments = question.getQues_comments();
        comments.add(savedComment);
        question.setQues_comments(comments);
    }

    public void deleteCommentById (Long id) {
        commentRepository.deleteById(id);
    }

    public void deleteCommentByQuestionId (Long id) {
        Question question = questionRepository.findById(id).get();
        commentRepository.deleteCommentsByQuestionId(id);
    }

    public void deleteCommentByAnswerId (Long id) {
        Question question = questionRepository.findById(id).get();
        commentRepository.deleteCommentsByAnswerId(id);
    }

}

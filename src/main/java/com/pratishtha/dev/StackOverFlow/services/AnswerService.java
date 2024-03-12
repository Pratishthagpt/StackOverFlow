package com.pratishtha.dev.StackOverFlow.services;

import com.pratishtha.dev.StackOverFlow.exceptions.UserNotFoundException;
import com.pratishtha.dev.StackOverFlow.model.*;
import com.pratishtha.dev.StackOverFlow.repositories.AnswerRepository;
import com.pratishtha.dev.StackOverFlow.repositories.QuestionRepository;
import com.pratishtha.dev.StackOverFlow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;
    private UserRepository userRepository;

    private QuestionRepository questionRepository;


    @Autowired
    public AnswerService(AnswerRepository answerRepository, UserRepository userRepository,
                         QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public Answer getAnswerById (Long id) {
        Optional<Answer> answerOptional = answerRepository.findById(id);
        if (answerOptional.isEmpty()) {
            return null;
        }
        return answerOptional.get();
    }

    public Answer addAnswer(String text, List<String> photoUrl, String userEmail, Long questionId) {

//        find user who is going to ask question and also checking that it should be the member
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User is not member. Please register with valid email.");
        }
        User answeredBy = userOptional.get();

//        add photos
        List<Photo> photos = new ArrayList<>();
        for (String url : photoUrl) {
            Photo photo = new Photo();
            photo.setUrl(url);
            photo.setUser(answeredBy);
            photos.add(photo);
        }

        Answer answer = new Answer();
        answer.setText(text);
        answer.setAnsStatus(PostStatus.OPEN);
        answer.setSatisfiedSolution(false);
        answer.setCreatedByMember(answeredBy);
        answer.setPhotos(photos);
        answer.setMembersWhoDownvoted(new HashSet<>());
        answer.setMembersWhoUpvoted(new HashSet<>());
        answer.setNoOfMemberWhoReported(0);

        Answer savedAnswer = answerRepository.save(answer);

//        add Answer to question
        addAnswerToQuestion(questionId, savedAnswer);

        return savedAnswer;
    }

    private void addAnswerToQuestion(Long questionId, Answer savedAnswer) {
        Question question = questionRepository.findById(questionId).get();
        List<Answer> answers = question.getAnswers();
        answers.add(savedAnswer);
        question.setAnswers(answers);
    }

    public void deleteAnswerById (Long id) {
        answerRepository.deleteById(id);
    }

    public void deleteAnswersByQuestionId (Long id) {
        Question question = questionRepository.findById(id).get();
        answerRepository.deleteAnswersByQuestionId(id);
    }
}

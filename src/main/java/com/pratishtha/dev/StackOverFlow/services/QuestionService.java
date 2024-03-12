package com.pratishtha.dev.StackOverFlow.services;

import com.pratishtha.dev.StackOverFlow.exceptions.TagNotFoundException;
import com.pratishtha.dev.StackOverFlow.exceptions.UserNotFoundException;
import com.pratishtha.dev.StackOverFlow.model.*;
import com.pratishtha.dev.StackOverFlow.repositories.QuestionRepository;
import com.pratishtha.dev.StackOverFlow.repositories.TagRepository;
import com.pratishtha.dev.StackOverFlow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }


    public Question getQuestionById (Long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if (questionOptional.isEmpty()) {
            return null;
        }
        return questionOptional.get();
    }

    public List<Question> getQuestionByTags (String text) {
        Tag tag = tagRepository.findTagByText(text);
        if (tag == null) {
            throw new TagNotFoundException("No such tag present.");
        }
        return questionRepository.findQuestionsByTags(text);
    }

    public Question addQuestion(String text, List<String> photoUrl, String userEmail, String title,
                                List<String> tags) {

//        find user who is going to ask question and also checking that it should be the member
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User is not member. Please register with valid email.");
        }
        User askedBy = userOptional.get();


        Question question = new Question();
        question.setText(text);
        question.setTitle(title);
        question.setQuesStatus(PostStatus.OPEN);
        question.setCreatedByMember(askedBy);
        question.setAnswers(new ArrayList<>());
        question.setQues_comments(new ArrayList<>());
        question.setMembersWhoDownvoted(new HashSet<>());
        question.setMembersWhoUpvoted(new HashSet<>());
        question.setNoOfMemberWhoReported(0);

//        add photos
        List<Photo> photos = getPhotosForQuestion(question, photoUrl, askedBy);
        question.setPhotos(photos);

//        add Tags
        List<Tag> tagsList = getTagsForQuestion(question, tags);
        question.setTags(tagsList);

        Question savedQuestion = questionRepository.save(question);
        return savedQuestion;
    }

    public void deleteQuestionById (Long id) {
        questionRepository.deleteById(id);
    }

    public List<Tag> getTagsForQuestion (Question question, List<String> tags) {
        List<Tag> tagsList = new ArrayList<>();
        for (String tagText : tags) {
            Tag tag = tagRepository.findTagByText(tagText);
            if (tag != null) {
                tagsList.add(tag);
            }
            else {
                Tag tag1 = new Tag();
                tag.setText(tagText);
                Tag savedTag = tagRepository.save(tag1);
                tagsList.add(savedTag);
            }
        }
        return tagsList;
    }

    public List<Photo> getPhotosForQuestion (Question question, List<String> photoUrl, User member) {
        List<Photo> photos = new ArrayList<>();
        for (String url : photoUrl) {
//            Photo photo = new Photo(url, member);
            Photo photo = new Photo();
            photo.setUrl(url);
            photo.setUser(member);
            photos.add(photo);
        }
        return photos;
    }

}

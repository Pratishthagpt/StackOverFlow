package com.pratishtha.dev.StackOverFlow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Answer extends PostContent{
    @Column
    private boolean isSatisfiedSolution;

    @OneToMany
    @JoinColumn(name = "answer")
    private List<Comment> ans_comments;

    @Enumerated(EnumType.STRING)
    @Column
    private PostStatus ansStatus;

    @ManyToOne
    @JoinColumn(name = "answers")
    private Question question;


//    public Answer(String text, List<Photo> photos, User answeredBy) {
//        super(text, photos, answeredBy);
//        this.isSatisfiedSolution = false;
//        this.comments = new ArrayList<>();
//        this.ansStatus = PostStatus.DEFAULT;
//    }

}

package com.pratishtha.dev.StackOverFlow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Question extends PostContent{
    @Column
    private String title;

    @OneToOne
    private Bounty bounty;

    @Enumerated(EnumType.STRING)
    @Column
    private PostStatus quesStatus;

    @ManyToMany
    @Column
    private List<Tag> tags;

    @OneToMany
    @JoinColumn(name = "question")
    private List<Comment> ques_comments;

    @OneToMany
    @Column
    private List<Answer> answers;

//    public Question(String text, List<Photo> photos, User quesAsker, String title) {
//        super(text, photos, quesAsker);
//        this.title = title;
//        this.quesStatus = PostStatus.OPEN;
//        this.bounty = new Bounty();
//        this.tags = new ArrayList<>();
//        if (tags != null) {
//            this.tags = tags;
//        }
//
//        this.comments = new ArrayList<>();
//        this.answers = new ArrayList<>();
//    }
}

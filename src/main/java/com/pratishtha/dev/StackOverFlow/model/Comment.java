package com.pratishtha.dev.StackOverFlow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Comment extends PostContent{

    @Enumerated(EnumType.STRING)
    @Column
    private PostStatus commentStatus;

    @ManyToOne
    private Question question;
    @ManyToOne
    private Answer answer;

//    public Comment(String text, List<Photo> photos, User commentedBy) {
//        super(text, photos, commentedBy);
//        this.commentStatus = PostStatus.DEFAULT;
//    }
}

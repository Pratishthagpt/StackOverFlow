package com.pratishtha.dev.StackOverFlow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Photo extends BaseModel{
    @Column
    private String url;

    @ManyToOne
    private User user;

    @Column
    private long createdDate;

//    public Photo(String url, User createdBy) {
//        this.url = url;
//        this.createdDate = System.currentTimeMillis();
//        this.createdBy = createdBy;
//    }
}

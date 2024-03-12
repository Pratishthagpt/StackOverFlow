package com.pratishtha.dev.StackOverFlow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Tag extends BaseModel{
    @Column
    private String text;

    @ManyToMany(mappedBy = "tags")
    private List<Question> questions;

//    public Tag(String text) {
//        this.text = text;
//    }
}

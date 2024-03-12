package com.pratishtha.dev.StackOverFlow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

// This is abstract class that has all the common properties of all entities
// making it as abstract class, because we won't be making an object of this class
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updatedAt;

//    public BaseModel(Long id) {
//        this.id = id;
//        this.createdAt = new Date();
//        this.updatedAt = new Date();
//    }
}

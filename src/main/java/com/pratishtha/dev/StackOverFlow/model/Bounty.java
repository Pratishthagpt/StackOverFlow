package com.pratishtha.dev.StackOverFlow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table
public class Bounty extends BaseModel{
    @Column
    private int reputation;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date expirationDate;
//    public Bounty(int reputation, Date expirationDate) {
//        this.reputation = reputation;
//        this.expirationDate = expirationDate;
//    }
}

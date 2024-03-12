package com.pratishtha.dev.StackOverFlow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "general_user")
public class User extends BaseModel{

    @Column(name = "full_name")
    private String name;
    @Column
    private String username;
    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @Column
    private int reputation;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Column
    private boolean isModerator;
    @Column
    private boolean isAdmin;
    @Column
    private boolean isMember;

//    @OneToMany
//    private List<Photo> photoList;

//    public User(String name, String username, String email, AccountStatus accountStatus,
//                int reputation) {
//        this.name = name;
//        this.username = username;
//        this.email = email;
//        this.accountStatus = accountStatus;
//        this.reputation = reputation;
//        this.role = Role.UN_REGISTERED;
//        this.isModerator = false;
//        this.isMember = false;
//        this.isAdmin = false;
//    }

    public void setMember() {
        if (!isMember) {
            isMember = true;
            this.setRole(Role.MEMBER);
        }
    }
    public void setModerator() {
        if (!isModerator) {
            isModerator = true;
            this.setRole(Role.MODERATOR);
        }
    }

    public void setAdmin() {
        if (!isAdmin) {
            isAdmin = true;
            this.setRole(Role.ADMIN);
        }
    }
}

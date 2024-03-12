package com.pratishtha.dev.StackOverFlow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


// This is abstract class that has all the common properties of question, answers or comments
// making it as abstract class, because we won't be making an object of this class
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class PostContent extends BaseModel{
//    @Column
    private String text;

    @ManyToMany
//    @Column
    private List<Photo> photos;
//    @Column
    private int noOfMemberWhoReported;
//    @Column
    private Set<Integer> membersWhoUpvoted;
//    @Column
    private Set<Integer> membersWhoDownvoted;

//    @Column
    @ManyToOne
    private User createdByMember;

//    public PostContent(String text, List<Photo> photos, User createdBy) {
//        super();
//        this.text = text;
//        this.photos = new ArrayList<>();
//        if (photos != null) {
//            photos = photos;
//        }
//        this.noOfMemberWhoReported = 0;
//        this.membersWhoUpvoted = new HashSet<>();
//        this.membersWhoDownvoted = new HashSet<>();
//        this.createdBy = createdBy;
//    }

}

package com.pratishtha.dev.StackOverFlow.controllers;

import com.pratishtha.dev.StackOverFlow.dtos.BlockAndUnBLockDTO;
import com.pratishtha.dev.StackOverFlow.dtos.BountyDTO;
import com.pratishtha.dev.StackOverFlow.dtos.PostContentDTO.*;
import com.pratishtha.dev.StackOverFlow.dtos.UserDTO.UserLoginRequestDTO;
import com.pratishtha.dev.StackOverFlow.dtos.UserDTO.UserResponseDTO;
import com.pratishtha.dev.StackOverFlow.dtos.UserDTO.UserSignUpRequestDTO;
import com.pratishtha.dev.StackOverFlow.exceptions.InvalidAuthorizationException;
import com.pratishtha.dev.StackOverFlow.exceptions.QuestionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class StackOverFlowController {

    private AdminController adminController;
    private MemberController memberController;
    private ModeratorController moderatorController;
    private UserController userController;
    private Scanner sc = new Scanner(System.in);

    @Autowired
    public StackOverFlowController(AdminController adminController, MemberController memberController,
                                   ModeratorController moderatorController, UserController userController) {
        this.adminController = adminController;
        this.memberController = memberController;
        this.moderatorController = moderatorController;
        this.userController = userController;
    }

    public UserResponseDTO signUpUser () {
        System.out.println("Please enter the name of user -");
        String name = sc.nextLine();

        System.out.println("Please enter the userName of user - ");
        String username = sc.nextLine();

        System.out.println("Please enter the email of user - ");
        String email = sc.nextLine();

        System.out.println("Please enter the role of user - \n 1. MEMBER \n 2. MODERATOR \n 3. AdMIN");
        int roleIndx = sc.nextInt();

        String role = "MEMBER";
        if (roleIndx == 1) {
            role = "MEMBER";
        }
        else if (roleIndx == 2) {
            role = "MODERATOR";
        }
        else if (roleIndx == 3) {
            role = "ADMIN";
        }

        UserSignUpRequestDTO userSignUpRequestDTO = new UserSignUpRequestDTO();
        userSignUpRequestDTO.setName(name);
        userSignUpRequestDTO.setUserName(username);
        userSignUpRequestDTO.setEmail(email);
        userSignUpRequestDTO.setRole(role);

        return userController.signUpUser(userSignUpRequestDTO);
    }

    public UserResponseDTO loginUser () {
        System.out.println("Please enter the name of user - ");
        String name = sc.nextLine();

        System.out.println("Please enter the userName of user - ");
        String username = sc.nextLine();

        System.out.println("Please enter the email of user - ");
        String email = sc.nextLine();

        UserLoginRequestDTO userLoginRequestDTO = new UserLoginRequestDTO();
        userLoginRequestDTO.setName(name);
        userLoginRequestDTO.setUserName(username);
        userLoginRequestDTO.setEmail(email);

        return userController.loginUser(userLoginRequestDTO);
    }

    public QuestionResponseDTO addNewQuestion () {
        System.out.println("Enter your member email id - ");
        String userEmail = sc.nextLine();

        System.out.println("Enter the question text - ");
        String questionText = sc.nextLine();

        System.out.println("Enter the question title - ");
        String title = sc.nextLine();

        System.out.println("Enter the no. of photos you want to add - ");
        int photoUrlNo = sc.nextInt();

        List<String> photoUrls = new ArrayList<>();
        while (photoUrlNo > 0) {
            System.out.println("Enter the photo url - ");
            String url = sc.next();
            photoUrls.add(url);
            photoUrlNo--;
        }

        System.out.println("Enter the no. of tags you want to add - ");
        int tagsNo = sc.nextInt();

        List<String> tags = new ArrayList<>();
        while (tagsNo > 0) {
            System.out.println("Enter the tag - ");
            String tagText = sc.next();
            tags.add(tagText);
            tagsNo--;
        }

        QuestionRequestDTO questionRequestDTO = new QuestionRequestDTO();
        questionRequestDTO.setUserEmail(userEmail);
        questionRequestDTO.setText(questionText);
        questionRequestDTO.setPhotoUrl(photoUrls);
        questionRequestDTO.setTitle(title);
        questionRequestDTO.setTags(tags);

        QuestionResponseDTO responseDTO = memberController.addQuestion(questionRequestDTO);
        return responseDTO;
    }

    public AnswerResponseDTO addNewAnswer () {
        System.out.print("Enter your member email id - ");
        String userEmail = sc.nextLine();

        System.out.print("Enter the answer text - ");
        String answerText = sc.nextLine();

        Long questionId = null;
        System.out.println("Do you want to add comment to question - Y/N ");
        String input = sc.next();
        if (input.equalsIgnoreCase("Y")) {
            System.out.println("Enter the question id - ");
            questionId = sc.nextLong();
        }

        System.out.println("Enter the no. of photos you want to add - ");
        int photoUrlNo = sc.nextInt();

        List<String> photoUrls = new ArrayList<>();
        while (photoUrlNo > 0) {
            System.out.println("Enter the tag - ");
            String url = sc.nextLine();
            photoUrls.add(url);
            photoUrlNo--;
        }

        AnswerRequestDTO answerRequestDTO = new AnswerRequestDTO();
        answerRequestDTO.setText(answerText);
        answerRequestDTO.setPhotoUrl(photoUrls);
        answerRequestDTO.setUserEmail(userEmail);
        answerRequestDTO.setQuestionId(questionId);

        return memberController.addAnswer(answerRequestDTO);
    }

    public CommentResponseDTO addNewComment () {
        System.out.println("Enter your member email id - ");
        String userEmail = sc.nextLine();

        System.out.println("Enter the answer text - ");
        String commentText = sc.nextLine();

        Long questionId = null;
        System.out.println("Do you want to add comment to question - Y/N ");
        String input = sc.next();
        if (input.equalsIgnoreCase("Y")) {
            System.out.println("Enter the question id - ");
            questionId = sc.nextLong();
        }

        Long answerId = null;
        System.out.println("Do you want to add comment to answer - Y/N ");
        input = sc.next();
        if (input.equalsIgnoreCase("Y")) {
            System.out.println("Enter the answer id - ");
            answerId = sc.nextLong();
        }

        System.out.println("Enter the no. of photos you want to add - ");
        int photoUrlNo = sc.nextInt();

        List<String> photoUrls = new ArrayList<>();
        while (photoUrlNo > 0) {
            System.out.println("Enter the tag - ");
            String url = sc.nextLine();
            photoUrls.add(url);
            photoUrlNo--;
        }

        CommentRequestDTO commentRequestDTO = new CommentRequestDTO();
        commentRequestDTO.setText(commentText);
        commentRequestDTO.setPhotoUrl(photoUrls);
        commentRequestDTO.setUserEmail(userEmail);
        commentRequestDTO.setQuestionId(answerId);
        commentRequestDTO.setQuestionId(questionId);

        return memberController.addComment(commentRequestDTO);
    }

    public void giveBountyToReceiver () {
        System.out.println("Enter your member id - ");
        Long memberId = sc.nextLong();

        System.out.println("Enter the receiver id - ");
        Long receiverId = sc.nextLong();

        System.out.println("Enter the reputation points for bounty - ");
        int reputation = sc.nextInt();

        BountyDTO bountyDTO = new BountyDTO();
        bountyDTO.setMemberId(memberId);
        bountyDTO.setBountyReputation(reputation);
        bountyDTO.setReceiverId(receiverId);

        memberController.giveBountyToReceiver(bountyDTO);
    }

    public void upVoteEntity () {
        System.out.println("Enter your member id - ");
        Long memberId = sc.nextLong();

        System.out.println("Enter the post Content type you want to up vote - Question/Answer/Comment");
        String type = sc.next();

        System.out.println("Enter the post Content id you want to up vote - ");
        Long entityId = sc.nextLong();

        PostContentDTO postContentDTO = new PostContentDTO();
        postContentDTO.setMemberId(memberId);
        postContentDTO.setPostContentId(entityId);
        postContentDTO.setPostContentType(type);

        memberController.upVoteEntity(postContentDTO);
    }

    public void downVoteEntity () {
        System.out.println("Enter your member id - ");
        Long memberId = sc.nextLong();

        System.out.println("Enter the post Content type you want to up vote - Question/Answer/Comment");
        String type = sc.next();

        System.out.println("Enter the post Content id you want to up vote - ");
        Long entityId = sc.nextLong();

        PostContentDTO postContentDTO = new PostContentDTO();
        postContentDTO.setMemberId(memberId);
        postContentDTO.setPostContentId(entityId);
        postContentDTO.setPostContentType(type);

        memberController.downVoteEntity(postContentDTO);
    }

    public void deleteEntity () {
        System.out.println("Enter your member id - ");
        Long memberId = sc.nextLong();

        System.out.println("Enter the post Content type you want to up vote - Question/Answer/Comment");
        String type = sc.next();

        System.out.println("Enter the post Content id you want to up vote - ");
        Long entityId = sc.nextLong();

        PostContentDTO postContentDTO = new PostContentDTO();
        postContentDTO.setMemberId(memberId);
        postContentDTO.setPostContentId(entityId);
        postContentDTO.setPostContentType(type);
        memberController.deleteEntity( postContentDTO);
    }

    public void closeQuestion () {
        System.out.println("Enter your member id - ");
        Long memberId = sc.nextLong();

        System.out.println("Enter the question id you want to close - ");
        Long questionId = sc.nextLong();

        PostContentDTO postContentDTO = new PostContentDTO();
        postContentDTO.setMemberId(memberId);
        postContentDTO.setPostContentId(questionId);
        moderatorController.closeQuestion(postContentDTO);
    }

    public void closeAnswer () {
        System.out.println("Enter your member id - ");
        Long memberId = sc.nextLong();

        System.out.println("Enter the answer id you want to close - ");
        Long answerId = sc.nextLong();

        PostContentDTO postContentDTO = new PostContentDTO();
        postContentDTO.setMemberId(memberId);
        postContentDTO.setPostContentId(answerId);
        moderatorController.closeAnswer(postContentDTO);
    }

    public void reOpenQuestion () {
        System.out.println("Enter your member id - ");
        Long memberId = sc.nextLong();

        System.out.println("Enter the question id you want to re-open - ");
        Long questionId = sc.nextLong();

        PostContentDTO postContentDTO = new PostContentDTO();
        postContentDTO.setMemberId(memberId);
        postContentDTO.setPostContentId(questionId);
        moderatorController.reOpenQuestion(postContentDTO);
    }

    public void blockMember () {
        System.out.println("Enter your member id - ");
        Long memberId = sc.nextLong();

        System.out.println("Enter the other member id you want to block - ");
        Long memberIdToBeBlocked = sc.nextLong();

        BlockAndUnBLockDTO blockAndUnBLockDTO = new BlockAndUnBLockDTO();
        blockAndUnBLockDTO.setMemberIdWhoWillBlock(memberId);
        blockAndUnBLockDTO.setMemberIdToBeBlocked(memberIdToBeBlocked);
        adminController.blockMember(blockAndUnBLockDTO);
    }

    public void unBlockMember () {
        System.out.println("Enter your member id - ");
        Long memberId = sc.nextLong();

        System.out.println("Enter the other member id you want to unblock - ");
        Long memberIdToBeBlocked = sc.nextLong();

        BlockAndUnBLockDTO blockAndUnBLockDTO = new BlockAndUnBLockDTO();
        blockAndUnBLockDTO.setMemberIdWhoWillBlock(memberId);
        blockAndUnBLockDTO.setMemberIdToBeBlocked(memberIdToBeBlocked);
        adminController.unBlockMember(blockAndUnBLockDTO);
    }
}

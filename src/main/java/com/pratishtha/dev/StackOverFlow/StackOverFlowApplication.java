package com.pratishtha.dev.StackOverFlow;

import com.pratishtha.dev.StackOverFlow.controllers.StackOverFlowController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
public class StackOverFlowApplication implements CommandLineRunner {

	@Autowired
	private StackOverFlowController stackOverFlowController;

	public static void main(String[] args) {
		SpringApplication.run(StackOverFlowApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);

//		1. Sign up
		System.out.println("Please enter the details for signing up to stack over flow platform - ");
		stackOverFlowController.signUpUser();

		System.out.println("Congratulations! You are now the member of stack over flow platform.");

//		2. Login
		System.out.println("Please login to stack over flow platform - ");
		stackOverFlowController.loginUser();

		System.out.println("Welcome, you are now logged in to the platform.");

		System.out.println("Enter the feature that you want to use - \n " +
				"1. Add new question\n 2. Add new answer\n 3. Add new comment\n 4. Give bounty\n " +
				"5. Upvote\n 6. Downvote\n 7. Delete Quetion/Answer/comment \n " +
				"8. Close Question \n 9. Close Answer \n 10 Re-Open Question\n " +
				"11. Block member\n 12. Un-block member");

		int input = sc.nextInt();

		switch (input) {
			case 1:
//				3. Add Question
				stackOverFlowController.addNewQuestion();
				break;
			case 2:
		//		4. Add Answer
				stackOverFlowController.addNewAnswer();
				break;
			case 3:
		//		5. Add Comment
				stackOverFlowController.addNewComment();
				break;
			case 4:
//				6. Give bounty
				stackOverFlowController.giveBountyToReceiver();
				break;
			case 5:
//				7. Upvote
				stackOverFlowController.upVoteEntity();
				break;
			case 6:
//				8. Downvote
				stackOverFlowController.downVoteEntity();
				break;
			case 7:
//				9. Delete Content
				stackOverFlowController.deleteEntity();
				break;
			case 8:
//				10. Close Question
				stackOverFlowController.closeQuestion();
				break;
			case 9:
//				11. Close Answer
				stackOverFlowController.closeAnswer();
				break;
			case 10:
//				12. Re-Open Question
				stackOverFlowController.reOpenQuestion();
				break;
			case 11:
//				13. Block Member
				stackOverFlowController.blockMember();
				break;
			case 12:
//				14. Un-Block Member
				stackOverFlowController.unBlockMember();
				break;
		}
	}
}

package com.pratishtha.dev.StackOverFlow.services;

import com.pratishtha.dev.StackOverFlow.exceptions.UserNotFoundException;
import com.pratishtha.dev.StackOverFlow.model.AccountStatus;
import com.pratishtha.dev.StackOverFlow.model.Role;
import com.pratishtha.dev.StackOverFlow.model.User;
import com.pratishtha.dev.StackOverFlow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUser(String name, String username, String email, String role) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }

//        User user = new User(name, username, email, AccountStatus.ACTIVE, 0);
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setReputation(0);
        user.setModerator(false);
        user.setAdmin(false);
        user.setMember(true);
        user.setRole(Role.MEMBER);

        if (role.equals("Moderator")) {
            user.setRole(Role.MODERATOR);
        }
        else if (role.equals("Admin")) {
            user.setRole(Role.ADMIN);
        }

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User loginUser(String name, String username, String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found, please go to signUp page.");
        }
        User user = userOptional.get();
        return user;
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    public boolean isUserMember (String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isUserModerator (String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return false;
        }
        User member = userOptional.get();
        if (!member.getRole().equals(Role.MODERATOR)) {
            return false;
        }
        return true;
    }

    public boolean isUserAdmin (String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return false;
        }
        User member = userOptional.get();
        if (!member.getRole().equals(Role.ADMIN)) {
            return false;
        }
        return true;
    }

    public User getMember (Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User is not member. Please register with valid email.");
        }
        User member = userOptional.get();
        return member;
    }


}

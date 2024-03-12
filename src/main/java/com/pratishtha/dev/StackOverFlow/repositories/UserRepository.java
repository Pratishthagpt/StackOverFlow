package com.pratishtha.dev.StackOverFlow.repositories;

import com.pratishtha.dev.StackOverFlow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long id);
    Optional<User> findByEmail (String email);

    @Override
    User save(User user);

    @Override
    void delete(User entity);

    @Override
    void deleteById(Long id);
}

package com.pratishtha.dev.StackOverFlow.repositories;

import com.pratishtha.dev.StackOverFlow.model.Answer;
import com.pratishtha.dev.StackOverFlow.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Override
    Optional<Comment> findById(Long id);

    @Override
    Comment save(Comment comment);

    @Override
    void deleteById(Long id);

    @Query("SELECT c FROM Comment c JOIN c.question q WHERE q.id = :questionId")
    List<Comment> findCommentsByQuestion (@Param("questionId") Long questionId);

    @Query("SELECT c FROM Comment c JOIN c.answer a WHERE a.id = :answerId")
    List<Comment> findCommentsByAnswer (@Param("answerId") Long answerId);
    void deleteCommentsByQuestionId (@Param("questionId") Long questionId);
    void deleteCommentsByAnswerId (@Param("answerId") Long answerId);
}

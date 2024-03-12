package com.pratishtha.dev.StackOverFlow.repositories;

import com.pratishtha.dev.StackOverFlow.model.Answer;
import com.pratishtha.dev.StackOverFlow.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Override
    Optional<Answer> findById(Long id);

    @Override
    Answer save(Answer answer);

    @Override
    void deleteById(Long id);

    @Query("SELECT a FROM Answer a JOIN a.question q WHERE q.id = :questionId")
    List<Answer> findAnswersByQuestion (@Param("questionId") Long questionId);

    void deleteAnswersByQuestionId (@Param("questionId")Long questionId);
}

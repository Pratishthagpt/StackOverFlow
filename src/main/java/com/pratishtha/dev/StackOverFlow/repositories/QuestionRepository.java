package com.pratishtha.dev.StackOverFlow.repositories;

import com.pratishtha.dev.StackOverFlow.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Override
    Optional<Question> findById(Long id);

    @Override
    Question save(Question question);

    @Override
    void deleteById(Long id);

    @Query("SELECT q FROM Question q JOIN q.tags t WHERE t.text = :tagText")
    List<Question> findQuestionsByTags (@Param("tagText") String tagText);
}

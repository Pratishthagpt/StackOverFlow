package com.pratishtha.dev.StackOverFlow.repositories;

import com.pratishtha.dev.StackOverFlow.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("SELECT t FROM Tag t WHERE t.text = :tagText")
    Tag findTagByText(@Param("tagText") String tagText);

    @Override
    Optional<Tag> findById(Long aLong);

    @Override
    Tag save(Tag entity);

    @Query("SELECT t FROM Tag t JOIN t.questions q WHERE q.id = :questionId")
    List<Tag> findTagsByQuestionId (@Param("questionId") Long questionId);
}

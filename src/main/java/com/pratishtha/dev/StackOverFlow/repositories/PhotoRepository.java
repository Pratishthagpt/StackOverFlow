package com.pratishtha.dev.StackOverFlow.repositories;

import com.pratishtha.dev.StackOverFlow.model.Photo;
import com.pratishtha.dev.StackOverFlow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findByUser (User user);

    @Override
    Optional<Photo> findById(Long id);

    @Override
    Photo save (Photo photo);

    @Override
    void deleteById(Long id);

    void deleteByUser (@Param("user") User user);

}

package com.thai.blog.service;

import com.thai.blog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGeneralService <T>{
    Iterable<T> findAll(Pageable pageable);

    Optional<T> findById(Long id);

    void save(T model);

    void remove(Long id);

    Page<T> findAllAuthorStartsWith(String author, Pageable pageable);
    Page<T>  findAllByCategory(Optional<Category> category, Pageable pageable);
}

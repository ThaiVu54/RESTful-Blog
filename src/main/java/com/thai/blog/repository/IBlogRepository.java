package com.thai.blog.repository;

import com.thai.blog.model.Blog;
import com.thai.blog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IBlogRepository extends PagingAndSortingRepository<Blog, Long> {
//public interface IBlogRepository extends CrudRepository<Blog, Long> {
    Page<Blog> fillAllByCategory(Optional<Category> category, Pageable pageable);
    Page<Blog> findAllByAuthorStartsWith(String author, Pageable pageable);
}


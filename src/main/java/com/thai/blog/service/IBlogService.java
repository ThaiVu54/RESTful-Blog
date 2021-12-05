package com.thai.blog.service;

import com.thai.blog.model.Blog;
import com.thai.blog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBlogService extends IGeneralService<Blog> {
    Page<Blog> findAllByCategory(Optional<Category> category, Pageable pageable);
    Page<Blog> findAllAuthorStartsWith(String author, Pageable pageable);
}

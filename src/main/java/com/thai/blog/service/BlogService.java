package com.thai.blog.service;

import com.thai.blog.model.Blog;
import com.thai.blog.model.Category;
import com.thai.blog.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {

    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public Page<Blog> findAllByCategory(Optional<Category> category, Pageable pageable) {
        return blogRepository.fillAllByCategory(category, pageable);
    }

    @Override
    public Iterable<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public void save(Blog model) {
        blogRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> findAllAuthorStartsWith(String author, Pageable pageable) {
        return blogRepository.findAllByAuthorStartsWith(author, pageable);
    }
}

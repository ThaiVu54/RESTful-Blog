package com.thai.blog.service;

import com.thai.blog.model.Category;
import com.thai.blog.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void save(Category model) {
        categoryRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<Category> findAllAuthorStartsWith(String author, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Category> findAllByCategory(Optional<Category> category, Pageable pageable) {
        return null;
    }
}

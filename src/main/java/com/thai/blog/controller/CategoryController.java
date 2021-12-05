package com.thai.blog.controller;

import com.thai.blog.model.Blog;
import com.thai.blog.model.Category;
import com.thai.blog.service.BlogService;
import com.thai.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;


@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<Page<Category>> listCategory(Pageable pageable) {
        Page<Category> categories = (Page<Category>) categoryService.findAll(pageable);
        if (categories.getTotalElements() == 0) {
            return new ResponseEntity<Page<Category>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Category>>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long id, Pageable pageable) {
        Optional<Category> category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        Page<Blog> blogs = blogService.findAllByCategory(category, pageable);
        return new ResponseEntity<Category>(HttpStatus.OK);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public ResponseEntity<Void> createCategory(@RequestBody Category category, UriComponentsBuilder ucBuilder) {
        categoryService.save(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/categories/{id}").buildAndExpand(category.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
        Optional<Category> currentCategory = categoryService.findById(id);
        if (currentCategory == null) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        category.setId(currentCategory.get().getId());
        category.setName(currentCategory.get().getName());
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }

}

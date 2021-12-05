package com.thai.blog.controller;

import com.thai.blog.model.Blog;
import com.thai.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Controller
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    @RequestMapping(value = "/blogs", method = RequestMethod.GET)
    //todo: cach 1
    public ResponseEntity<Page<Blog>> showListBlog(Pageable pageable) {
        Page<Blog> blogs = (Page<Blog>) blogService.findAll(pageable);
        if (blogs.getTotalElements() == 0) {
            return new ResponseEntity<Page<Blog>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Blog>>(blogs, HttpStatus.OK);
    }
    //todo: cach 2
//    public ResponseEntity<Iterable<Blog>> findAllCustomer(Pageable pageable) {
//        List<Blog> customers = (List<Blog>) blogService.findAll(pageable);
//        if (customers.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(customers,HttpStatus.OK);
//    }

    @RequestMapping(value = "/blogs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blog> getBlog(@PathVariable ("id") Long id){
        Optional<Blog> blog = blogService.findById(id);
        if (!blog.isPresent()){
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Blog>(blog.get(),HttpStatus.OK);
    }


    @RequestMapping(value = "/blogs", method = RequestMethod.POST)
    public ResponseEntity<Void> createBlog(@RequestBody Blog blog, UriComponentsBuilder uriComponentsBuilder){
        blogService.save(blog);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/blog/{id}").buildAndExpand(blog.getId()).toUri());
        return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/blogs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Blog> updateBlog(@PathVariable ("id") Long id, @RequestBody Blog blog){
        Optional<Blog> currentBlog = blogService.findById(id);
        if (!currentBlog.isPresent()){
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }
        blog.setAuthor(currentBlog.get().getAuthor());
        blog.setContent(currentBlog.get().getContent());
        blogService.save(blog);
        return new ResponseEntity<Blog>(HttpStatus.OK);
    }

    @RequestMapping(value = "/blogs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Blog> deleteBlog(@PathVariable ("id") Long id){
        Optional<Blog> blog = blogService.findById(id);
        if (!blog.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blogService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

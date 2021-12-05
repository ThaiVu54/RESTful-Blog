package com.thai.blog.formatter;

import com.thai.blog.model.Category;
import com.thai.blog.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class CategoryFormatter implements Formatter<Optional<Category>> {
    @Autowired
    private ICategoryService categoryService;

    public CategoryFormatter(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    //chuyen doi du lieu dau vao sang du lieu yeu cau
    public Optional<Category> parse(String text, Locale locale) throws ParseException {
        return categoryService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Optional<Category> object, Locale locale) {
        return "";
    }
}

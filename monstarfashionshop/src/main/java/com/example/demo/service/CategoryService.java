package com.example.demo.service;

import com.example.demo.entity.Category;

import java.util.List;

public interface CategoryService {

    Category findCategoryByName(String categoryName);

    List<Category> getListCategory();


}

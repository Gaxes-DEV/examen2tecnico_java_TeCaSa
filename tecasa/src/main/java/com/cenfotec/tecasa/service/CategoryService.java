package com.cenfotec.tecasa.service;

import com.cenfotec.tecasa.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public void saveCategory (Category category);
    public List<Category> getAllCategories();
    public Optional<Category> findById(Long id);
    public void deleteCategory(Long id);
}

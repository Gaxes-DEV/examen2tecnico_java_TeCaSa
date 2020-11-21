package com.cenfotec.tecasa.service;

import com.cenfotec.tecasa.domain.Category;
import com.cenfotec.tecasa.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository repo;

    @Override
    public void saveCategory(Category category){ repo.save(category); }

    @Override
    public List<Category> getAllCategories(){ return repo.findAll(); }

    @Override
    public Optional<Category> findById(Long id){ return repo.findById(id); }

    @Override
    public void deleteCategory(Long id){ repo.deleteById(id); }
}

package com.workintech.s19challenge.service.product;

import com.workintech.s19challenge.entity.product.Category;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.repository.product.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        throw new GlobalException("Category is not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category delete(long id) {
        Category category = findById(id);
        if(category != null){
            categoryRepository.delete(category);
            return category;
        }
        throw new GlobalException("Category is not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}

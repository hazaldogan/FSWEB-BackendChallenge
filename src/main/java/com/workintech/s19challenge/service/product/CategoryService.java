package com.workintech.s19challenge.service.product;


import com.workintech.s19challenge.entity.product.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(long id);
    Category save(Category category);
    Category delete(long id);
}

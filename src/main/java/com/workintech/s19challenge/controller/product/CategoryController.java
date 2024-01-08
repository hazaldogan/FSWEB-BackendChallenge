package com.workintech.s19challenge.controller.product;

import com.workintech.s19challenge.dto.product.CategoryResponse;
import com.workintech.s19challenge.dto.product.CategoryResponseWithProduct;
import com.workintech.s19challenge.dto.product.ProductResponse;
import com.workintech.s19challenge.entity.product.Category;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.service.product.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category save(@RequestBody Category category){
        return categoryService.save(category);
    }

    @GetMapping
    public List<CategoryResponse> findAll(){
        List<Category> categoryList = categoryService.findAll();
        List<CategoryResponse> newList = new ArrayList<>();
        categoryList.forEach(category -> {
            newList.add(new CategoryResponse(category.getId(), category.getTitle(), category.getGender()));
        });
        return newList;
    }

    @GetMapping("/{id}")
    public CategoryResponseWithProduct findById(@PathVariable long id){
        Category category = categoryService.findById(id);
        List<ProductResponse> productResponses = new ArrayList<>();
        category.getProductList().forEach(product -> {
            productResponses.add(new ProductResponse(product.getId(), product.getName(),
                    product.getDescription(), product.getPrice(), product.getStock(), product.getRating()));
        });
        return new CategoryResponseWithProduct(category.getId(), category.getTitle(), category.getGender(),
                productResponses);
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable long id, @RequestBody Category category){
        Category foundCategory = categoryService.findById(id);
        if(foundCategory != null){
            categoryService.save(category);
        }else{
            throw new GlobalException("Category is not found with id: "+ id, HttpStatus.NOT_FOUND);
        }
        return new CategoryResponse(category.getId(), category.getTitle(), category.getGender());
    }

    @DeleteMapping("/{id}")
    public CategoryResponse delete(@PathVariable long id){
        Category category = categoryService.findById(id);
        if(category != null){
            categoryService.delete(id);
            return new CategoryResponse(category.getId(), category.getTitle(), category.getGender());
        }
        throw new GlobalException("Category is not found with id: " + id,HttpStatus.NOT_FOUND);
    }
}

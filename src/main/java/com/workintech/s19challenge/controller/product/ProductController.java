package com.workintech.s19challenge.controller.product;

import com.workintech.s19challenge.dto.product.ProductResponse;
import com.workintech.s19challenge.entity.product.Category;
import com.workintech.s19challenge.entity.product.Product;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.service.product.CategoryService;
import com.workintech.s19challenge.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public ProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }
    
    @GetMapping
    public List<Product> findAll(){
        return productService.findAll();
    }
    
    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable long id){
        Product product = productService.findById(id);
        if(product != null){
            return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                    product.getPrice(), product.getStock(), product.getRating());
        }
        throw new GlobalException("Product is not found with id: " + id, HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/{categoryId}")
    public ProductResponse save(@PathVariable long categoryId, @RequestBody Product product){
        Category category = categoryService.findById(categoryId);
        if(category != null){
            category.getProductList().add(product);
            product.setCategory(category);
            productService.save(product);
        }else{
            throw new GlobalException("Category is not found", HttpStatus.NOT_FOUND);
        }
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getStock(), product.getRating());
    }

    @PutMapping("/{categoryId}")
    public ProductResponse update(@RequestBody Product product, @PathVariable long categoryId){
        Category category = categoryService.findById(categoryId);
        Product foundProduct = null;
        for(Product product1 : category.getProductList()){
            if(product.getId() == product1.getId()){
                foundProduct = product1;
            }
        }
        if(foundProduct == null){
            throw new GlobalException("Product is not found", HttpStatus.NOT_FOUND);
        }
        int indexOfFound = category.getProductList().indexOf(foundProduct);
        category.getProductList().set(indexOfFound,product);
        product.setCategory(category);
        productService.save(product);
        return new ProductResponse(product.getId(),product.getName(), product.getDescription(),
                product.getPrice(), product.getStock(), product.getRating());
    }

    @DeleteMapping("/{id}")
    public ProductResponse delete(@PathVariable long id){
        Product product = productService.findById(id);
        if(product!= null){
            productService.delete(id);
            return new ProductResponse(product.getId(),product.getName(), product.getDescription(),
                    product.getPrice(), product.getStock(), product.getRating());
        }else{
            throw new GlobalException("Product is not found", HttpStatus.NOT_FOUND);
        }
    }
    
}

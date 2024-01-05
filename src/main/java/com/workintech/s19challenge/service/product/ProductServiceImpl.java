package com.workintech.s19challenge.service.product;

import com.workintech.s19challenge.entity.product.Product;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        throw new GlobalException("Product is not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product delete(long id) {
        Product product = findById(id);
        if(product != null){
            productRepository.delete(product);
            return product;
        }
        throw new GlobalException("Product is not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}

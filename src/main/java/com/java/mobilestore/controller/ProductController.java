package com.java.mobilestore.controller;

import com.java.mobilestore.entity.Product;
import com.java.mobilestore.request.ProductRequest;
import com.java.mobilestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/admin/product/all")
    public ResponseEntity<List<Product>> getAllProduct() {
        try {
            List<Product> listProduct = productService.getAll();
            return new ResponseEntity<>(listProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(@ModelAttribute ProductRequest request) {
        try {
            Product product = new Product();
            product.setProductName(request.getProductName());
            product.setUnitPrice(request.getUnitPrice());
            product.setUnitInStock(request.getUnitInStock());
            product.setDescription(request.getDescription());
            product.setManufacturer(request.getManufacturer());
            product.setCategory(request.getCategory());
            product.setCondition(request.getCondition());
            product.setImage(request.getImage());
            return new ResponseEntity<>(productService.saveOrUpDate(product), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}

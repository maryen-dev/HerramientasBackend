package com.marly.demo.web.controller;

import com.marly.demo.Domain.Product;
import com.marly.demo.Domain.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable("id") int productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/category/{categoryId}")
    public Optional<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId);
    }

    @PostMapping("/save")
    public Product save(@RequestPart("product") Product product,
                        @RequestParam(value = "imageUrl", required = false) String imageUrl) throws IOException {
        return productService.save(product, imageUrl);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int productId) {
        return productService.delete(productId);
    }

    @PutMapping(value = "/update/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @ModelAttribute Product product,
            @RequestParam(value = "imageUrl", required = false) String imageUrl
    ) throws IOException {
        product.setProductId(id);
           Product updated = productService.update(product, imageUrl);
        return ResponseEntity.ok(updated);
    }
}

package com.marly.demo.Domain.Service;


import org.springframework.web.multipart.MultipartFile;

import com.marly.demo.Domain.Product;

import com.marly.demo.Domain.Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import com.marly.demo.Domain.Service.FileStorageService;




@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileStorageService fileStorageService;



    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product, String imageUrl) throws IOException {
        // Si se recibe una URL, se asigna directamente
        if (imageUrl != null && !imageUrl.isEmpty()) {
            product.setProductImage(imageUrl);
        } else {
            throw new IllegalArgumentException("URL de la imagen no proporcionada");
        }
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
     return getProduct(productId).map(product -> {
            if (product.getProductImage() != null) {
                Path imagePath = Paths.get(fileStorageService.getUploadDir()).resolve(product.getProductImage());
                try {
                    Files.deleteIfExists(imagePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }

   


public Product update(Product product, String imageUrl) throws IOException {
        return getProduct(product.getProductId()).map(existing -> {
            try {
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    if (existing.getProductImage() != null) {
                        Path oldImage = Paths.get(fileStorageService.getUploadDir()).resolve(existing.getProductImage());
                        Files.deleteIfExists(oldImage);
                    }
                    product.setProductImage(imageUrl);
                } else {
                    product.setProductImage(existing.getProductImage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return productRepository.update(product);
        }).orElse(product);
    }
}

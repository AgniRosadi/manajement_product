package com.manajement_produk.manajement_produk.controller;

import com.manajement_produk.manajement_produk.model.ApiResponse;
import com.manajement_produk.manajement_produk.model.Product;
import com.manajement_produk.manajement_produk.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    // 1. GET /products: Mendapatkan daftar semua produk.
    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> getAllProducts = productRepository.findAll();
        ApiResponse<List<Product>> apiResponse = new ApiResponse<>(200, "success", getAllProducts);
        return ResponseEntity.ok(apiResponse);
    }

    // 2. POST /products: Menambahkan produk baru.
    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@Valid @RequestBody Product product) {
        product.setStatus("Pending");  // Default status saat produk baru ditambahkan
        Product savedProduct = productRepository.save(product);
        ApiResponse<Product> apiResponse = new ApiResponse<>(200, "success", savedProduct);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    // 3. PUT /products/{id}: Mengedit produk yang ada.
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id, @Valid @RequestBody Product productDetails) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Product product = productOptional.get();
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setDescription(productDetails.getDescription());
        Product updatedProduct = productRepository.save(product);
        ApiResponse<Product> apiResponse = new ApiResponse<>(200, "updated", updatedProduct);
        return ResponseEntity.ok(apiResponse);
    }

    // 4. DELETE /products/{id}: Menghapus produk.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // 5. GET /products/pending: Mendapatkan daftar produk yang menunggu approval.
    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<Product>>> getPendingProducts() {
        List<Product> updatedProduct = productRepository.findByStatus("Pending");
        ApiResponse<List<Product>> apiResponse = new ApiResponse<>(200, "success", updatedProduct);
        return ResponseEntity.ok(apiResponse);

    }

    // 6. PUT /products/{id}/approve: Menyetujui produk.
    @PutMapping("/{id}/approve")
    public ResponseEntity<ApiResponse<Product>> approveProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product product = productOptional.get();
        product.setStatus("Approved");
        Product approveProduct = productRepository.save(product);
        ApiResponse<Product> apiResponse = new ApiResponse<>(200, "success", approveProduct);
        return ResponseEntity.ok(apiResponse);
    }

    // 7. PUT /products/{id}/reject: Menolak produk.
    @PutMapping("/{id}/reject")
    public ResponseEntity<ApiResponse<Product>> rejectProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Product product = productOptional.get();
        product.setStatus("Rejected");
        Product rejectProduct = productRepository.save(product);
        ApiResponse<Product> apiResponse = new ApiResponse<>(200, "success", rejectProduct);
        return ResponseEntity.ok(apiResponse);
    }
}



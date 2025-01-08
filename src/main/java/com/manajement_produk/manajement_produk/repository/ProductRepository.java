package com.manajement_produk.manajement_produk.repository;

import com.manajement_produk.manajement_produk.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStatus(String status);  // Query untuk mencari produk berdasarkan status
}


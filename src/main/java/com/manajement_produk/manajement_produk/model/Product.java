package com.manajement_produk.manajement_produk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
    // Getter dan Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Jika id di-generate otomatis
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String status;

    // Konstruktor default (tanpa parameter)
    public Product() {
        // Konstruktor default harus ada
    }

    // Konstruktor dengan parameter (opsional)
    public Product(String name, Double price, String description, String status) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
    }

}

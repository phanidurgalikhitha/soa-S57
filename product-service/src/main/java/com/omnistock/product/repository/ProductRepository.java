package com.omnistock.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omnistock.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
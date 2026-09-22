package com.omnistock.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omnistock.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
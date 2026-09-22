package com.omnistock.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.omnistock.order.entity.Order;
import com.omnistock.order.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestClient.Builder restClientBuilder;

    public OrderService(
            OrderRepository orderRepository,
            RestClient.Builder restClientBuilder) {

        this.orderRepository = orderRepository;
        this.restClientBuilder = restClientBuilder;
    }

    public Order createOrder(Order order) {

        String inventoryUrl =
                "http://localhost:8082/inventory/reduce/"
                + order.getProductId()
                + "/"
                + order.getQuantity();

        RestClient restClient = restClientBuilder.build();

        try {

            restClient.put()
                    .uri(inventoryUrl)
                    .retrieve()
                    .body(String.class);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to update inventory. Order was not created.");
        }

        order.setStatus("PLACED");

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
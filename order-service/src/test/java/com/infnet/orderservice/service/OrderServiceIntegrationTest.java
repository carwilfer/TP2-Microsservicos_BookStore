package com.infnet.orderservice.service;

import com.infnet.orderservice.model.Order;
import com.infnet.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
class OrderServiceIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testCreateAndFindOrder() {
        // Criação de uma ordem
        Order order = new Order();
        order.setBookTitle("Test Book");
        order.setQuantity(2);
        order.recalculateTotalPrice();
        orderRepository.save(order);

        // Verificação da ordem criada
        Order foundOrder = orderRepository.findById(order.getId()).orElse(null);
        assertNotNull(foundOrder);
        assertEquals("Test Book", foundOrder.getBookTitle());
    }

    @Test
    void testOrderDeletion() {
        // Criação de uma ordem
        Order order = new Order();
        order.setBookTitle("Test Book to Delete");
        order.setQuantity(1);
        order.recalculateTotalPrice();
        orderRepository.save(order);

        // Exclusão da ordem
        orderRepository.deleteById(order.getId());

        // Verificação da exclusão
        Order deletedOrder = orderRepository.findById(order.getId()).orElse(null);
        assertNull(deletedOrder);
    }
}
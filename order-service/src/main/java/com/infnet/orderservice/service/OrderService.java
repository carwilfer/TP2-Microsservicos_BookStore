package com.infnet.orderservice.service;

import com.infnet.orderservice.client.BookClient;
import com.infnet.orderservice.client.CustomerClient;
import com.infnet.orderservice.dto.OrderDTO;
import com.infnet.orderservice.model.Order;
import com.infnet.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private BookClient bookClient;

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<OrderDTO> getOrderById(Long id) {
        return orderRepository.findById(id).map(this::convertToDTO);
    }

    @Transactional
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        // Chama o FeignClient para buscar informações do livro
        ResponseEntity<Object> bookResponse = bookClient.getBookById(orderDTO.getId());
        if (!bookResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Book not found");
        }

        // Chama o FeignClient para buscar informações do cliente
        ResponseEntity<Object> customerResponse = customerClient.getCustomerById(orderDTO.getId());
        if (!customerResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Customer not found");
        }

        Order order = convertToEntity(orderDTO);
        order.recalculateTotalPrice();
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setBookTitle(order.getBookTitle());
        dto.setQuantity(order.getQuantity());
        dto.setTotalPrice(order.getTotalPrice());
        return dto;
    }

    private Order convertToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setBookTitle(dto.getBookTitle());
        order.setQuantity(dto.getQuantity());
        order.setTotalPrice(dto.getTotalPrice());
        return order;
    }
}
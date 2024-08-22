package com.infnet.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookTitle;
    private int quantity;
    private double totalPrice;

    // Método para aumentar a quantidade e recalcular o preço total
    public void increaseQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.quantity += amount;
        recalculateTotalPrice();
    }

    // Método para recalcular o preço total com base na quantidade
    public void recalculateTotalPrice() {
        double pricePerBook = 10.0; // Exemplo de preço fixo
        this.totalPrice = this.quantity * pricePerBook;
    }
}
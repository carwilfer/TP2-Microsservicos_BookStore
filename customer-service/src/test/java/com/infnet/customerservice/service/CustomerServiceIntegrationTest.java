package com.infnet.customerservice.service;

import com.infnet.customerservice.entity.Customer;
import com.infnet.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testCreateAndFindCustomer() {
        // Criação de um cliente
        Customer customer = new Customer();
        customer.setName("Test Customer");
        customer.setEmail("test@customer.com");
        customerRepository.save(customer);

        // Verificação do cliente criado
        Customer foundCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertNotNull(foundCustomer);
        assertEquals("Test Customer", foundCustomer.getName());
    }

    @Test
    void testCustomerDeletion() {
        // Criação de um cliente
        Customer customer = new Customer();
        customer.setName("Test Customer to Delete");
        customer.setEmail("delete@customer.com");
        customerRepository.save(customer);

        // Exclusão do cliente
        customerRepository.deleteById(customer.getId());

        // Verificação da exclusão
        Customer deletedCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertNull(deletedCustomer);
    }
}
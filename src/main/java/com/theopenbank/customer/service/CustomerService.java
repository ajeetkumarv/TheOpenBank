package com.theopenbank.customer.service;

import com.theopenbank.customer.dto.CustomerRequest;
import com.theopenbank.customer.dto.CustomerResponse;
import com.theopenbank.customer.model.Customer;
import com.theopenbank.customer.model.Gender;
import com.theopenbank.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Get all customers
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get one by ID
    public Optional<CustomerResponse> getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(this::mapToResponse);
    }

    // Create new customer
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setMiddleName(request.getMiddleName());
        customer.setLastName(request.getLastName());
        customer.setDateOfBirth(request.getDateOfBirth());

        // Map enum → smallint code
        Gender gender = request.getGender() != null ? request.getGender() : Gender.UNKNOWN;
        customer.setGender(gender);

        Customer saved = customerRepository.save(customer);
        return mapToResponse(saved);
    }

    // Delete customer
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    // Helper: map entity → response DTO
    private CustomerResponse mapToResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setMiddleName(customer.getMiddleName());
        response.setLastName(customer.getLastName());
        response.setDateOfBirth(customer.getDateOfBirth());
        response.setCreatedAt(customer.getCreatedAt());

        // Convert smallint code → enum
        response.setGender(customer.getGender());

        return response;
    }
}

package com.d2y.spring_elasticsearch.controllers;

import com.d2y.spring_elasticsearch.dto.CustomerRequestDto;
import com.d2y.spring_elasticsearch.dto.CustomerResponseDto;
import com.d2y.spring_elasticsearch.models.Customer;
import com.d2y.spring_elasticsearch.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        Customer customer = mapToCustomerEntity(customerRequestDto);
        Customer savedCustomer = customerService.saveCustomer(customer);
        CustomerResponseDto customerResponseDto = mapToCustomerResponseDto(savedCustomer);
        return ResponseEntity.ok(customerResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        List<CustomerResponseDto> customerResponseDtos = customers.stream()
                .map(this::mapToCustomerResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerResponseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable String id) {
        Optional<Customer> customer = customerService.findCustomerById(id);
        if (customer.isPresent()) {
            CustomerResponseDto customerResponseDto = mapToCustomerResponseDto(customer.get());
            return ResponseEntity.ok(customerResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable String id, @RequestBody CustomerRequestDto customerDetails) {
        Optional<Customer> customer = customerService.findCustomerById(id);
        if (customer.isPresent()) {
            Customer updatedCustomer = mapToCustomerEntity(customerDetails);
            updatedCustomer.setId(id);  // Set the ID for the update
            Customer savedCustomer = customerService.saveCustomer(updatedCustomer);
            CustomerResponseDto customerResponseDto = mapToCustomerResponseDto(savedCustomer);
            return ResponseEntity.ok(customerResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    private Customer mapToCustomerEntity(CustomerRequestDto dto) {
        return new Customer(null, dto.getFirstName(), dto.getLastName(), dto.getEmail());
    }

    private CustomerResponseDto mapToCustomerResponseDto(Customer customer) {
        return new CustomerResponseDto(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }
}
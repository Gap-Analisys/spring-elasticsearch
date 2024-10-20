package com.d2y.spring_elasticsearch.services;


import com.d2y.spring_elasticsearch.models.Customer;
import com.d2y.spring_elasticsearch.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAllCustomers() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Customer> customersPage = customerRepository.findAll(pageable);
        return customersPage.getContent();
    }

    public Optional<Customer> findCustomerById(String id) {
        return customerRepository.findById(id);
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}

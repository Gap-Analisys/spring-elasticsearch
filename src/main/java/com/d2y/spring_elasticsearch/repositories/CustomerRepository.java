package com.d2y.spring_elasticsearch.repositories;

import com.d2y.spring_elasticsearch.models.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {
}

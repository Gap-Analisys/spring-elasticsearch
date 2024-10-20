package com.d2y.spring_elasticsearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "customers")
public class Customer {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;
}

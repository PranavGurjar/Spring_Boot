package com.customer.config;

import com.customer.model.Customer;
import org.springframework.batch.item.ItemProcessor;

public class UserItemProcessor implements ItemProcessor<Customer, Customer> {
    @Override
    public Customer process(Customer customer) throws Exception {
        return customer;
    }
}

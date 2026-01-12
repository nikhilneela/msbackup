package org.learning.lld.repository;

import lombok.NonNull;
import org.learning.lld.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCustomerRepository implements ICustomerRepository {
    private final List<Customer> customers;

    public InMemoryCustomerRepository() {
        this.customers = new ArrayList<>();
    }

    @Override
    public void save(@NonNull Customer customer) {
        this.customers.add(customer);
    }

    @Override
    public Customer get(@NonNull String id) {
        return this.customers.stream().filter(customer -> customer.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Customer getByEmail(@NonNull String emailId) {
        return this.customers.stream().filter(customer -> customer.getEmail().equals(emailId)).findFirst().orElse(null);
    }
}

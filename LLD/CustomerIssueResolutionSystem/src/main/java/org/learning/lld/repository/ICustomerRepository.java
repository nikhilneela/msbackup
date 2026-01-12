package org.learning.lld.repository;

import lombok.NonNull;
import org.learning.lld.model.Customer;

public interface ICustomerRepository {
    void save(@NonNull final Customer customer);
    Customer get(@NonNull final String id);
    Customer getByEmail(@NonNull final String emailId);
}

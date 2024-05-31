package org.shiba.module.dao;

import org.shiba.module.model.Customer;

import java.util.List;

public interface ICustomerDao {
    List<Customer> findAll();
    Customer findById(Integer id);
    public void save(Customer customer);
    void delete(Integer id);
}

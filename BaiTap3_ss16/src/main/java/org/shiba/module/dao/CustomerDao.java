package org.shiba.module.dao;

import org.shiba.module.model.Customer;
import org.shiba.module.util.Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerDao implements ICustomerDao{

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        Connection conn = Mysql.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM customer");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setStatus(rs.getBoolean("status"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("test " + Arrays.toString(customers.toArray()));
        return customers;
    }

    @Override
    public Customer findById(Integer id) {
        Connection conn = Mysql.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM customer WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setStatus(rs.getBoolean("status"));
                return customer;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

 @Override
 public void save(Customer customer) {
        Connection conn = Mysql.getConnection();
     System.out.println("test " + customer.toString());

        if (customer.getId() == null){
            try{
                PreparedStatement ps = conn.prepareStatement("INSERT INTO customer (name, address, status) VALUES (?, ?, ?)");
                ps.setString(1, customer.getName());
                ps.setString(2, customer.getAddress());
                ps.setBoolean(3, customer.getStatus());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            try{
                PreparedStatement ps = conn.prepareStatement("UPDATE customer SET name = ?, address = ?, status = ? WHERE id = ?");
                ps.setString(1, customer.getName());
                ps.setString(2, customer.getAddress());
                ps.setBoolean(3, customer.getStatus());
                ps.setInt(4, customer.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        Connection conn = Mysql.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM customer WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Mysql.closeConnection(conn);
        }

    }
}

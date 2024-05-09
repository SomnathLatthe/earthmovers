package com.jaybhavani.earthmovers.service;

import com.jaybhavani.earthmovers.entity.Customer;
import com.jaybhavani.earthmovers.repository.CustomerRepo;
import com.jaybhavani.earthmovers.utility.SystemGeneratedDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private SystemGeneratedDate systemGeneratedDate;

    public void saveCustomer(Customer customer)
    {
        String dt=systemGeneratedDate.getDate();
        customer.setCreated_on(dt);
        customerRepo.save(customer);
    }

    public List<Customer> getAllCustomer()
    {
        List<Customer> customerList=customerRepo.findAll();
        return customerList;
    }

    public Customer findById(int id)
    {
        Customer customer=customerRepo.findById(id).get();
        return customer;
    }
    public void deleteCustomer(int id)
    {
        customerRepo.deleteById(id);
    }
}

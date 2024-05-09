package com.jaybhavani.earthmovers.repository;

import com.jaybhavani.earthmovers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    public Customer findByName(String name);
}

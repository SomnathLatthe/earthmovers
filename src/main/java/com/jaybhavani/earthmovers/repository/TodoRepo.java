package com.jaybhavani.earthmovers.repository;

import com.jaybhavani.earthmovers.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface TodoRepo extends JpaRepository<Todo,Long> {

    @Query(value = "SELECT * FROM Todo WHERE name = ?1", nativeQuery = true)
    List<Todo> findByCustomerId(int id);
}

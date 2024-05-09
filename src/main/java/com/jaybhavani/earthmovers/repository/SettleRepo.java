package com.jaybhavani.earthmovers.repository;

import com.jaybhavani.earthmovers.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettleRepo extends JpaRepository<Settlement,Integer> {
}

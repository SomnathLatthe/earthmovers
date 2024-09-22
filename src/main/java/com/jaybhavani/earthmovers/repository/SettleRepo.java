package com.jaybhavani.earthmovers.repository;

import com.jaybhavani.earthmovers.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SettleRepo extends JpaRepository<Settlement,Long> {

   /* @Modifying
    @Query("update Settlement s set s.date = ?1, s.name = ?2 , s.price = ?3 where s.id = ?4")
    void updateSettlement(String date, String name,String price, Long id);*/
}

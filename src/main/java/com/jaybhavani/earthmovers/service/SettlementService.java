package com.jaybhavani.earthmovers.service;

import com.jaybhavani.earthmovers.entity.Customer;
import com.jaybhavani.earthmovers.entity.Settlement;
import com.jaybhavani.earthmovers.repository.CustomerRepo;
import com.jaybhavani.earthmovers.repository.SettleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SettlementService {
    @Autowired
    private SettleRepo settleRepo;
    @Autowired
    private CustomerRepo customerRepo;

    public List<Settlement> getAllSettlements()
    {
        List<Settlement> settlements1 = new ArrayList<>();
        List<Settlement> settlements=settleRepo.findAll();
        for(Settlement i: settlements)
        {
            Settlement obj = new Settlement();
            obj.setDate(i.getDate());
            obj.setPrice(i.getPrice());
            obj.setId(i.getId());
            Customer customer=customerRepo.findById(Integer.parseInt(i.getName())).get();
            obj.setName(customer.getName());
            settlements1.add(obj);
        }
        return settlements1;
    }

    public void saveSettlement(Settlement settlement)
    {
        settleRepo.save(settlement);
    }

    public void deleteSettlement(Long id)
    {
        settleRepo.deleteById((id));
    }

    public Settlement getById(Long id)
    {
        Settlement settlement1=settleRepo.findById((id)).get();
        return settlement1;
    }
}

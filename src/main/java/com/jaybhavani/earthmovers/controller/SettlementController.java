package com.jaybhavani.earthmovers.controller;

import com.jaybhavani.earthmovers.entity.Customer;
import com.jaybhavani.earthmovers.entity.Settlement;
import com.jaybhavani.earthmovers.service.CustomerService;
import com.jaybhavani.earthmovers.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SettlementController {
    @Autowired
    private SettlementService settlementService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/getAllSettlements")
    public ModelAndView getAllSettlements()
    {
        List<Settlement> settlements=settlementService.getAllSettlements();
        return new ModelAndView("settle/getAllSettlements","settlement",settlements);
    }

   /* @RequestMapping("/addSettlement")
    public String addSettlement(Model model)
    {
        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        List<Customer> customerList = customerService.getAllCustomer();
        model.addAttribute("customerList",customerList);
        return "settle/addSettlement";
    }*/
    @RequestMapping("/addEditSettlement")///{id}
    public String editSettlement(@RequestParam(value = "id", required = false) Long id, Model model)//@PathVariable("id") int id
    {
        System.out.println("inside editSettlement SettlementId: "+id);
        Settlement settlement=null;
        if(id!=null) {
            settlement = settlementService.getById(id);
        }
        else{
            settlement = new Settlement();
        }

        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        List<Customer> customerList = customerService.getAllCustomer();
        model.addAttribute("customerList",customerList);

        model.addAttribute("settlement",settlement);
        return "settle/addSettlement";
    }
    @RequestMapping("/saveSettlement")
    public String saveSettlement(@ModelAttribute Settlement settlement)
    {
        settlementService.saveSettlement(settlement);
        return "redirect:/getAllSettlements";
    }
    @RequestMapping("/deleteSettlement/{id}")
    public String deleteSettlement(@PathVariable("id") Long id)
    {
        settlementService.deleteSettlement(id);
        return "redirect:/getAllSettlements";
    }


}

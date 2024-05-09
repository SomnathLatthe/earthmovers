package com.jaybhavani.earthmovers.controller;

import com.jaybhavani.earthmovers.entity.Customer;
import com.jaybhavani.earthmovers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MasterController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/")
    public String testMe()
    {
        return "home";
    }

    @RequestMapping("/new_customer")
    public String newCustomer()
    {
        return "new_customer";
    }
    @RequestMapping("/save_customer")
    public String addCustomer(@ModelAttribute Customer customer)
    {
        customerService.saveCustomer(customer);
        return "redirect:/customer_list";
    }

    @RequestMapping("/customer_list")
    public ModelAndView getAllCustomer()
    {
        List<Customer> customerList = customerService.getAllCustomer();
        return new ModelAndView("customer_list","customer",customerList);
    }

    @RequestMapping("/editCustomer/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model)
    {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer",customer);
        return "editCustomer";
    }

    @RequestMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable("id") int id)
    {
        customerService.deleteCustomer(id);
        return "redirect:/customer_list";
    }
}

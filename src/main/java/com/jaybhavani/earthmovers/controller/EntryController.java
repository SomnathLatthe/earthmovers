package com.jaybhavani.earthmovers.controller;

import com.jaybhavani.earthmovers.entity.Customer;
import com.jaybhavani.earthmovers.entity.Todo;
import com.jaybhavani.earthmovers.entity.TotalPriceDTO;
import com.jaybhavani.earthmovers.service.CustomerService;
import com.jaybhavani.earthmovers.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class EntryController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private TodoService todoService;

    @RequestMapping("/addTodaysEntry")
    public String addEntry(Model model)
    {
        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        List<Customer> customerList = customerService.getAllCustomer();
        model.addAttribute("customerList",customerList);
        return "entry/addTodaysEntry";
    }

    @RequestMapping("/saveTodaysEntry")
    public String saveTodaysEntry(@ModelAttribute Todo todo)
    {
        System.out.println("***name: "+todo.getName());
        todoService.saveTodo(todo);
        return "redirect:/getAllEntryList";
    }

    @RequestMapping("/getAllEntryList")
    public ModelAndView getAllEntries()
    {
        List<Todo> entries = todoService.getAllEntries();
        return new ModelAndView("entry/getAllEntryList","todo",entries);
    }

    @RequestMapping("/deleteEntry/{id}")
    public String deleteEntry(@PathVariable("id") int id)
    {
        todoService.deleteEntry(id);
        return "redirect:/getAllEntryList";
    }
    @RequestMapping("/editEntry/{id}")
    public String editEntry(@PathVariable("id") int id,Model model)
    {
        Todo todo=todoService.findById(id);

        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        List<Customer> customerList = customerService.getAllCustomer();
        model.addAttribute("customerList",customerList);

        model.addAttribute("entry",todo);
        return "entry/editEntry";
    }

   @RequestMapping("/getTotalPriceReport")
   public String showTotalPriceByCustomer(Model model) {
       List<TotalPriceDTO> totalPriceDTOList = todoService.getTotalPriceByCustomer();
       model.addAttribute("totalPriceList", totalPriceDTOList);
       // return "total_price_template"; // Thymeleaf template name
        return "entry/getTotalByName";
    }

    @RequestMapping("/getEntryDetails/{id}")
    public ModelAndView getEntryDetails(@PathVariable("id") int id)
    {
        System.out.println("getEntryDetails id: "+id);
        List<Todo> todos=todoService.findByCustomerId(id);
        return new ModelAndView("entry/getEntryDetails","todos",todos);
    }

}

package com.jaybhavani.earthmovers.service;

import com.jaybhavani.earthmovers.entity.Customer;
import com.jaybhavani.earthmovers.entity.Todo;
import com.jaybhavani.earthmovers.entity.TotalPriceDTO;
import com.jaybhavani.earthmovers.repository.CustomerRepo;
import com.jaybhavani.earthmovers.repository.TodoRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private CustomerRepo customerRepo;


    public void saveTodo(Todo todo)
    {
        todoRepo.save(todo);
    }

    public List<Todo> getAllEntries()
    {
        List<Todo> todoList1=new ArrayList<>();
        try {

            List<Todo> todoList = todoRepo.findAll();
            for (Todo value : todoList) {
                Todo todo = new Todo();
                todo.setArea(value.getArea());
                todo.setDate(value.getDate());
                todo.setCategory(value.getCategory());
                todo.setId(value.getId());
                todo.setHours(value.getHours());
                todo.setMachine(value.getMachine());
                todo.setPrice(value.getPrice());
                if (value.getName() != null && !value.getName().equals("")) {
                    int id = Integer.parseInt(value.getName());
                    String customerName="";
                    try {
                        Customer customer = customerRepo.findById(id).get();
                        customerName=customer.getName();
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    todo.setName(customerName);
                } else {
                    todo.setName("");
                }
                todoList1.add(todo);

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Exception in TodoService.getAllEntries:"+e.getMessage());
        }
        return todoList1;
    }

    public void deleteEntry(int id)
    {
        todoRepo.deleteById(id);
    }

    public Todo findById(int id)
    {
        Todo todo=todoRepo.findById(id).get();
        return todo;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<TotalPriceDTO> getTotalPriceByCustomer() {
        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT c.name, SUM(t.price) ,CAST(c.id AS string) FROM Todo t " +
                        "JOIN Customer c ON CAST(t.name AS int)=c.id GROUP BY c.name,c.id ", Object[].class);
        List<Object[]> results = query.getResultList();

        List<TotalPriceDTO> totalPriceDTOList = results.stream()
                .map(result -> new TotalPriceDTO((String) result[0], bigDecimalToDouble(new BigDecimal(""+result[1])),(String) result[2]))
                .collect(Collectors.toList());

        return totalPriceDTOList;
    }

    public static Double bigDecimalToDouble(BigDecimal bigDecimal) {
        if (bigDecimal != null) {
            System.out.println("Value: "+bigDecimal);
            return bigDecimal.doubleValue();
        } else {
            // Handle null BigDecimal case
            System.out.println("Handle null BigDecimal case");
            return null;
        }
    }

    public List<Todo> findByCustomerId(int id)
    {
        List<Todo> todos=todoRepo.findByCustomerId(id);
        return todos;
    }
}

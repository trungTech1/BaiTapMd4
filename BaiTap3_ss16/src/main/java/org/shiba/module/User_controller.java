package org.shiba.module;
import org.shiba.module.dao.CustomerDao;
import org.shiba.module.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class User_controller {
    @GetMapping("")
    public String list(Model model){
        CustomerDao customerDao = new CustomerDao();
    model.addAttribute("customers", customerDao.findAll());
    return "customer";
    }
    @GetMapping("/add")
    public String add(){
        return "addCustomer";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id){
        CustomerDao customerDao = new CustomerDao();
        model.addAttribute("customer", customerDao.findById(id));
        return "editCustomer";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        CustomerDao customerDao = new CustomerDao();
        customerDao.delete(id);
        return "redirect:/customer";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Customer customer){
        System.out.println("da vao" + customer);
        CustomerDao customerDao = new CustomerDao();
        customerDao.save(customer);
        return "redirect:/customer";
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute Customer customer){
        CustomerDao customerDao = new CustomerDao();
        customerDao.save(customer);
        return "redirect:/customer";
    }
}

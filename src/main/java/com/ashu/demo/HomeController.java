package com.ashu.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    AddressBookRepository addressBookRepository;

    @RequestMapping("/")
    public String listAddressBook(Model model){

        model.addAttribute("addresses",addressBookRepository.findAll());
        return "list";

    }

    @GetMapping("/add")
    public String addressBookForm(Model model) {
        model.addAttribute("addressBook", new AddressBook());
        return "addressform";
    }

    @PostMapping("/process")
    public String processForm(@Valid AddressBook address, BindingResult result) {
        if (result.hasErrors()) {
            return "addressform";
        }
        addressBookRepository.save(address);

        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showAddressBook(@PathVariable("id") long id, Model model) {
        model.addAttribute("addressBook", addressBookRepository.findOne(id));
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateAddressBook(@PathVariable("id") long id, Model model) {
        model.addAttribute("addressBook", addressBookRepository.findOne(id));

        return "addressform";
    }

    @RequestMapping("/delete/{id}")
    public String delAddressBook(@PathVariable("id") long id) {
        addressBookRepository.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/search")
    public String searchAddressBookByFirstName(@RequestParam("searchTerm") String searchTerm, Model model){

     List<AddressBook> addressBook= addressBookRepository.findByLastName(searchTerm);
     model.addAttribute("addresses",addressBook);


        return "list";
    }

}

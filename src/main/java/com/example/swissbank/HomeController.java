package com.example.swissbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    AccountRepository accountRepository;

    @RequestMapping("/")
    public String homePage(){
        return "index";
    }
    @RequestMapping("/transaction")
    public String transaction(Model model){
        model.addAttribute("accounts", accountRepository.findAll());
        return "transactionhistory";
    }
    @GetMapping("/add")
    public String depositForm(Model model){
        model.addAttribute("account", new Account());
        return "depositform";
    }
    @GetMapping("/minus")
    public String withdrawForm(Model model){
        model.addAttribute("account", new Account());
        return "withdrawform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Account account, BindingResult result)
    {
        if (result.hasErrors()){
            return "depositform";
        }
        accountRepository.save(account);
        return "redirect:/";
    }
    @PostMapping("/process2")
    public String process2Form(@Valid Account account, BindingResult result) {
        if (result.hasErrors()) {
            return "withdrawform";
        }
        accountRepository.save(account);
        return "redirect:/";
    }
    @RequestMapping("/balance")
    public String balance(Model model){
        model.addAttribute("accounts", accountRepository.findAll());
        return "balance";
    }

}

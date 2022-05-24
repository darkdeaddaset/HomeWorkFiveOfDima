package com.savin.controller;

import com.savin.model.User;
import com.savin.service.Search;
import com.savin.service.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private Validation validation;
    private Search search;

    @Autowired
    public UserController(Validation validation, Search search) {
        this.validation = validation;
        this.search = search;
    }

    @GetMapping("/user")
    public String userResult(Model model){
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping("/user")
    public String userSet(@ModelAttribute User user){
        if (validation.check(user)){
            return "complete";
        }
        return "error-add-user";
    }

    @GetMapping("/user-search")
    public String userSearch(Model model){
        model.addAttribute("user", new User());
        return "search";
    }

    @GetMapping("/user-search-result")
    public String userSearchResult(@ModelAttribute User user){
        user = search.search(user);

        if (user != null){
            return "result-searching";
        } else {
            return "redirect:/user-search-error";
        }
    }

    //Через представление
    @GetMapping("/user-search-error")
    public String userSearchError(){
        return "error-search-user";
    }

    //Через статус ошибки
    @GetMapping("/error-status")
    public ResponseEntity<?> getStatus(){
        return new ResponseEntity("Пользователь не найден!\n" + HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
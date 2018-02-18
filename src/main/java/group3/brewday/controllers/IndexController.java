package group3.brewday.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import group3.brewday.services.UserService;

@Controller
public class IndexController {
   
	@Autowired
	UserService userService;
	
    @GetMapping("/")
    public String root(Model model, Authentication auth) {
    	model.addAttribute("username",auth.getName());
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }


}

    

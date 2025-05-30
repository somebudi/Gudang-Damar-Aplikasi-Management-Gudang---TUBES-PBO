package com.gudangdamar.main.controller;

import com.gudangdamar.main.model.User;
import com.gudangdamar.main.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

  
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User()); 
        return "login"; 
    }


    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User userForm, Model model, HttpSession session) {
        User user = userRepository.findByUsername(userForm.getUsername());

        if (user != null && user.getPassword().equals(userForm.getPassword())) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/dashboard"; 
        } else {
            model.addAttribute("error", "Username atau password salah!");
            return "login";
        }
    }

   
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

package com.gudangdamar.main.controller;

import com.gudangdamar.main.model.User;
import com.gudangdamar.main.repository.UserRepository;
import com.gudangdamar.main.util.PasswordUtils;
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
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "pages/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User userForm, Model model, HttpSession session) {
        User user = userRepository.findByUsername(userForm.getUsername());

        if (user == null) {
            model.addAttribute("error", "Username atau password salah!");
            model.addAttribute("user", new User());
            return "pages/login";
        }

        boolean match = PasswordUtils.verifyPassword(
                userForm.getPassword(),
                user.getSalt(),
                user.getPasswordHash()
        );

        if (!match) {
            model.addAttribute("error", "Username atau password salah!");
            model.addAttribute("user", new User());
            return "pages/login";
        }

        session.setAttribute("loggedInUser", user);
        return "redirect:/halamanGudangBeranda";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "pages/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User userForm, Model model) {
        if (userRepository.findByUsername(userForm.getUsername()) != null) {
            model.addAttribute("error", "Username sudah digunakan.");
            return "pages/register";
        }

        // Generate salt sekali, hash password sekali, simpan ke objek User
        String salt = PasswordUtils.generateSalt();
        String hashedPassword;
        try {
            hashedPassword = PasswordUtils.hashPassword(userForm.getPassword(), salt);
        } catch (Exception e) {
            model.addAttribute("error", "Error saat memproses password.");
            return "pages/register";
        }

        userForm.setSalt(salt);
        userForm.setPasswordHash(hashedPassword);

        if (userForm.getRole() == null || userForm.getRole().isEmpty()) {
            userForm.setRole("kasir");  // default role
        }

        userRepository.save(userForm);

        model.addAttribute("success", "Registrasi berhasil. Silakan login.");
        model.addAttribute("user", new User());
        return "pages/login";
    }
}

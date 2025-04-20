package com.gudangdamar.main.controller;

import com.gudangdamar.main.model.User;
import com.gudangdamar.main.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        repo.save(user);
        System.out.println("Data masuk: " + user.getName() + " - " + user.getEmail());
        return ResponseEntity.ok("Sukses insert");
    }
}

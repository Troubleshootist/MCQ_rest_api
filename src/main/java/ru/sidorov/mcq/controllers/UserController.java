package ru.sidorov.mcq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sidorov.mcq.model.User;
import ru.sidorov.mcq.repository.UserRepo;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }




    @GetMapping("api/users")
    public Map<String, Object> allUsers () {
        Map<String, Object> model = new HashMap<>();
        Iterable<User> allUsers = userRepo.findAll();

        model.put("users", allUsers);
        return model;
    }

    @PostMapping("api/users")
    public User postUser(@RequestBody User user) {
        return userRepo.save(user);
    }
}

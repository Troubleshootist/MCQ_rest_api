package ru.sidorov.mcq.api_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.mcq.model.User;
import ru.sidorov.mcq.repository.UserRepo;
import ru.sidorov.mcq.services.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {
    private UserRepo userRepo;
    private UserService userService;
    
    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    

    @GetMapping
    public Map<String, Object> allUsers () {
        Map<String, Object> model = new HashMap<>();
        Iterable<User> allUsers = userRepo.findAll();

        model.put("users", allUsers);
        return model;
    }

    @PostMapping
    public User postUser(@RequestBody User user) {
        return userService.save(user);
    }



}

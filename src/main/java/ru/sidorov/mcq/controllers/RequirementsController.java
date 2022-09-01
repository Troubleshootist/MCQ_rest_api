package ru.sidorov.mcq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sidorov.mcq.repository.RequirementsRepo;

import java.util.HashMap;
import java.util.Map;


@RestController
public class RequirementsController {
    private RequirementsRepo requirementsRepo;

    @Autowired
    public void setRequirementsRepo(RequirementsRepo requirementsRepo) {
        this.requirementsRepo = requirementsRepo;
    }

    @GetMapping("/api/requirements")
    public Map<String, Object> getAllRequirements() {
        Map<String, Object> model = new HashMap<>();
        model.put("requirements", requirementsRepo.findAll());
        return model;
    }
}

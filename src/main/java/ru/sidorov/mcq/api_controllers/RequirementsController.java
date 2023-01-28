package ru.sidorov.mcq.api_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sidorov.mcq.repository.RequirementRepo;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/requirements")
public class RequirementsController {
    private RequirementRepo requirementRepo;

    @Autowired
    public void setRequirementsRepo(RequirementRepo requirementRepo) {
        this.requirementRepo = requirementRepo;
    }

    @GetMapping
    public Map<String, Object> getAllRequirements() {
        Map<String, Object> model = new HashMap<>();
        model.put("requirements", requirementRepo.findAll());
        return model;
    }
}

package ru.sidorov.mcq.api_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sidorov.mcq.repository.AtaChapterRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/chapters")
@CrossOrigin(origins = "http://localhost:8081")
public class AtaChapterController {
    private AtaChapterRepository ataChapterRepository;

    @Autowired
    public void setAtaChapterRepository(AtaChapterRepository ataChapterRepository) {
        this.ataChapterRepository = ataChapterRepository;
    }

    @GetMapping
    public Map<String, Object> getAllAtaChapters() {
        Map<String, Object> model = new HashMap<>();
        model.put("ata_chapters", ataChapterRepository.findAll(Sort.by(Sort.Direction.ASC, "ataDigit")));
        return model;
    }
}

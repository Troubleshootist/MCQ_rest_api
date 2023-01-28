package ru.sidorov.mcq.api_controllers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExamControllerTest{

    @Autowired
    private MockMvc mockMvc;




    @Test
    @WithMockUser(username = "admin", password = "adminadmin")
    public void examControllerTest() throws Exception {
        this.mockMvc.perform(get("/api/exams"))
                .andExpect(jsonPath("exams").isArray())
                .andExpect(jsonPath("exams", hasSize(greaterThan(5))))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "adminadmin")
    public void examByIdTest() throws Exception {
        ArrayList<Integer> validQuestionsNumber = new ArrayList<>(Arrays.asList(16, 20, 24, 28, 32, 34, 40, 44, 48, 52));
        this.mockMvc.perform(get("/api/exams/89"))
                .andExpect(jsonPath("questions_count", is(in(validQuestionsNumber))))
                .andExpect(status().isOk());
    }

    @Test
    public void unauthenticatedTest() throws Exception {
        this.mockMvc.perform(get("/api/exams"))
                .andExpect(status().isUnauthorized());
    }




}

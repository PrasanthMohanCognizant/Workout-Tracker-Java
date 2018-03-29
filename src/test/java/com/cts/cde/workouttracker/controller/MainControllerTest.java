package com.cts.cde.workouttracker.controller;

import com.cts.cde.workouttracker.model.Category;
import com.cts.cde.workouttracker.model.Workout;
import com.cts.cde.workouttracker.repository.CategoryRepository;
import com.cts.cde.workouttracker.repository.WorkoutRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.doNothing;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkoutRepository workoutRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void getWorkoutsTest() throws Exception{
        List<Workout> workoutList = new ArrayList<Workout>();
        Workout workout;
        workout= new Workout();
        workout.setId(1);
        workoutList.add(workout);
        workout = new Workout();
        workout.setId(2);
        workoutList.add(workout);

        given(workoutRepository.findAll()).willReturn(workoutList);

        mockMvc.perform(get("/api/workouts").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));

    }

    @Test
    public void getWorkoutTest() throws Exception{
        Workout workout = new Workout();
        workout.setId(1);

        given(workoutRepository.getOne(1)).willReturn(workout);

        mockMvc.perform(get("/api/workouts/1").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void searchWorkoutTest() throws Exception{
        List<Workout> workoutList = new ArrayList<Workout>();
        Workout workout;
        workout= new Workout();
        workout.setId(1);
        workoutList.add(workout);
        workout = new Workout();
        workout.setId(2);
        workoutList.add(workout);

        given(workoutRepository.findByTitle("Test")).willReturn(workoutList);

        mockMvc.perform(get("/api/workouts/search?title=Test").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    public void addWorkoutTest() throws Exception{
        Workout workout = new Workout();
        workout.setId(1);

        given(workoutRepository.save(workout)).willReturn(workout);

        mockMvc.perform(post("/api/workouts").contentType(MediaType.APPLICATION_JSON).content(asJsonString(workout)).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void updateWorkoutTest() throws Exception{
        Workout workout = new Workout();
        workout.setId(1);

        given(workoutRepository.save(workout)).willReturn(workout);

        mockMvc.perform(put("/api/workouts").contentType(MediaType.APPLICATION_JSON).content(asJsonString(workout)).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteWorkoutTest() throws Exception{

        Workout workout = new Workout();
        workout.setId(1);

        doNothing().when(workoutRepository).deleteById(1);

        mockMvc.perform(delete("/api/workouts/1").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void getCategoriesTest() throws Exception{
        List<Category> categoryList = new ArrayList<Category>();
        Category category;
        category= new Category();
        category.setId(1);
        categoryList.add(category);
        category = new Category();
        category.setId(2);
        categoryList.add(category);

        given(categoryRepository.findAll()).willReturn(categoryList);

        mockMvc.perform(get("/api/categories").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));

    }

    @Test
    public void getCategoryTest() throws Exception{
        Category category = new Category();
        category.setId(1);

        given(categoryRepository.getOne(1)).willReturn(category);

        mockMvc.perform(get("/api/categories/1").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void addCategoryTest() throws Exception{
        Category category = new Category();
        category.setId(1);

        given(categoryRepository.save(category)).willReturn(category);

        mockMvc.perform(post("/api/categories").contentType(MediaType.APPLICATION_JSON).content(asJsonString(category)).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCategoryTest() throws Exception{
        Category category = new Category();
        category.setId(1);

        given(categoryRepository.save(category)).willReturn(category);

        mockMvc.perform(put("/api/categories").contentType(MediaType.APPLICATION_JSON).content(asJsonString(category)).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCategoryTest() throws Exception{

        doNothing().when(categoryRepository).deleteById(1);

        mockMvc.perform(delete("/api/categories/1").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

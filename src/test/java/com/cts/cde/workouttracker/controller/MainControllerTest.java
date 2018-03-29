package com.cts.cde.workouttracker.controller;

import com.cts.cde.workouttracker.model.Category;
import com.cts.cde.workouttracker.model.Workout;
import com.cts.cde.workouttracker.repository.CategoryRepository;
import com.cts.cde.workouttracker.repository.WorkoutRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

//    @Test
//    public void addWorkoutTest() throws Exception{
//        Workout workout = new Workout();
//        workout.setId(1);
//
//        given(workoutRepository.save(workout)).willReturn(workout);
//
//        mockMvc.perform(post("/api/workouts").param("id","1").accept(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk());
//    }

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



}

package com.cts.cde.workouttracker.controller;

import com.cts.cde.workouttracker.model.Category;
import com.cts.cde.workouttracker.model.Workout;
import com.cts.cde.workouttracker.repository.CategoryRepository;
import com.cts.cde.workouttracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class MainController {

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/workouts")
    public List<Workout> getWorkouts(){
        return workoutRepository.findAll();
    }

    @GetMapping("/workouts/{id}")
    public ResponseEntity<Workout> getWorkout(@PathVariable(value = "id")int id){
        Workout workout = workoutRepository.getOne(id);
        return ResponseEntity.ok().body(workout);
    }

    @PostMapping("/workouts")
    public void addWorkout(@RequestBody Workout workout){

        workoutRepository.save(workout);
    }

    @PutMapping("/workouts")
    public void updateWorkout(@RequestBody Workout workout){

        workoutRepository.save(workout);
    }

//    @PostMapping
//    public void deleteWorkout(@PathVariable(value = "id")int id){
//
//        workoutRepository.deleteById(id);
//    }

    @GetMapping("/categories")
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable(value = "id")int id){
        Category category = categoryRepository.getOne(id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping("/categories")
    public void addCategory(@RequestBody Category category){

        categoryRepository.save(category);
    }

    @PutMapping("/categories")
    public void updateCategory(@RequestBody Category category){

        categoryRepository.save(category);
    }

//    @PostMapping
//    public void deleteCategory(@PathVariable(value = "id")int id){
//
//        categoryRepository.deleteById(id);
//    }

}

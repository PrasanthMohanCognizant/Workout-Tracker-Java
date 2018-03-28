package com.cts.cde.workouttracker.repository;

import com.cts.cde.workouttracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Integer>{

    @Query("SELECT w FROM Workout w WHERE w.title like concat(:title, '%')")
    List<Workout> findByTitle(@Param("title") String title);
}

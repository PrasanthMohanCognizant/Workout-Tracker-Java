package com.cts.cde.workouttracker.repository;

import com.cts.cde.workouttracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Integer>{
}

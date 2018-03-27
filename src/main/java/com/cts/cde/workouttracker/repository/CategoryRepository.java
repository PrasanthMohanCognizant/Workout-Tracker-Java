package com.cts.cde.workouttracker.repository;

import com.cts.cde.workouttracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

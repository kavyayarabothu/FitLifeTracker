package com.proj.FitLifeTracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proj.FitLifeTracker.entity.Meal;

public interface MealRepository extends JpaRepository<Meal,Long> {

}

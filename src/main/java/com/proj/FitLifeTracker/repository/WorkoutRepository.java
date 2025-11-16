package com.proj.FitLifeTracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proj.FitLifeTracker.entity.Workout;

public interface WorkoutRepository extends JpaRepository<Workout , Long> {

}

package com.proj.FitLifeTracker.service;
import java.util.List;
import com.proj.FitLifeTracker.entity.Workout;

public interface WorkoutService {
	List<Workout> getAllWorkouts();
	Workout getWorkoutById(Long id);
	Workout addWorkout(Workout workout);
	void deleteWorkout(Long id);
	
	
}

package com.proj.FitLifeTracker.controller;
import org.springframework.web.bind.annotation.*;
import com.proj.FitLifeTracker.entity.Workout;
import com.proj.FitLifeTracker.entity.User;
import com.proj.FitLifeTracker.service.WorkoutService;
import com.proj.FitLifeTracker.repository.UserRepository;
import java.util.List;

@RestController
@RequestMapping("/workouts")
@CrossOrigin(origins = "*")
public class WorkoutController {
	private final WorkoutService workoutService;
	private final UserRepository userRepository;
	
	public WorkoutController(WorkoutService workoutService, UserRepository userRepository) {
		this.workoutService = workoutService;
		this.userRepository = userRepository;
	}
	@PostMapping("/{userId}")
	public Workout addWorkout(@PathVariable Long userId,@RequestBody Workout workout) {
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new RuntimeException("User not found"));
		workout.setUser(user);
		return workoutService.addWorkout(workout);
	}
	@GetMapping
	public List<Workout> getAllWorkouts(){
		return workoutService.getAllWorkouts();
	}
	@GetMapping("/user/{userId}")
	public List<Workout> getWorkoutByUser(@PathVariable Long userId) {
		return userRepository.findById(userId)
				.map(user -> user.getWorkouts())
				.orElseThrow(()-> new RuntimeException("User not found"));
	}
	@DeleteMapping("/{id}")
	public String deleteWorkout(@PathVariable Long id) {
	    workoutService.deleteWorkout(id);
		return "Workout deleted successfully";	
	}
	

}

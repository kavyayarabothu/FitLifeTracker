package com.proj.FitLifeTracker.service.imp;
import java.util.List;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import com.proj.FitLifeTracker.entity.Workout;
import com.proj.FitLifeTracker.service.WorkoutService;
import com.proj.FitLifeTracker.repository.WorkoutRepository;
import java.util.Map;
import java.util.HashMap;

@Service
public class WorkoutServiceImpl implements WorkoutService {
	private final WorkoutRepository workoutRepository;
	private static final Map<String, Double> MET_VALUES = new HashMap<>();
    static {
    	 // Cardio & Aerobic
        MET_VALUES.put("walking", 3.5);
        MET_VALUES.put("jogging", 7.0);
        MET_VALUES.put("running", 8.3);
        MET_VALUES.put("treadmill", 7.5);
        MET_VALUES.put("aerobics", 6.5);
        MET_VALUES.put("zumba", 7.3);
        MET_VALUES.put("dance workout", 6.0);
        MET_VALUES.put("hiit", 9.0);
        MET_VALUES.put("jump rope", 11.0);
        MET_VALUES.put("stair climbing", 8.8);
        MET_VALUES.put("elliptical", 5.0);

        // Strength / Resistance Training
        MET_VALUES.put("weight lifting", 3.0);
        MET_VALUES.put("heavy weight lifting", 6.0);
        MET_VALUES.put("squats", 5.0);
        MET_VALUES.put("lunges", 5.5);
        MET_VALUES.put("leg press", 5.0);
        MET_VALUES.put("bench press", 5.5);
        MET_VALUES.put("push ups", 8.0);
        MET_VALUES.put("pull ups", 8.0);
        MET_VALUES.put("deadlifts", 6.0);
        MET_VALUES.put("plank", 4.0);
        MET_VALUES.put("crunches", 5.0);
        MET_VALUES.put("sit ups", 5.0);

        // Sports
        MET_VALUES.put("badminton", 5.5);
        MET_VALUES.put("basketball", 6.5);
        MET_VALUES.put("football", 7.0);
        MET_VALUES.put("cricket", 5.0);
        MET_VALUES.put("tennis", 7.3);
        MET_VALUES.put("swimming", 6.0);
        MET_VALUES.put("cycling", 6.8);
        MET_VALUES.put("boxing", 9.0);
        MET_VALUES.put("kickboxing", 8.0);
        MET_VALUES.put("martial arts", 10.0);

        // Flexibility / Mindful Activities
        MET_VALUES.put("yoga", 3.0);
        MET_VALUES.put("pilates", 3.5);
        MET_VALUES.put("stretching", 2.5);
        MET_VALUES.put("meditation", 1.3);

        // Default for unknown types
        MET_VALUES.put("default", 4.0);
    }
	
	
	public WorkoutServiceImpl (WorkoutRepository workoutRepository) {
		this.workoutRepository = workoutRepository;
	}
	@Override
	public Workout addWorkout(Workout workout) {
		double met = MET_VALUES.getOrDefault(workout.getWorkoutType().toLowerCase(),4.0);
		double hours = workout.getDuration() / 60.0;
		double caloriesBurned = met * workout.getWeight() * hours;
		
	    workout.setCaloriesBurned(caloriesBurned);
		
		if(workout.getDate() == null) {
			workout.setDate(LocalDate.now());
		}
		return workoutRepository.save(workout);
	}
	@Override
	public List<Workout> getAllWorkouts(){
		return workoutRepository.findAll();
	}
	@Override
	public Workout getWorkoutById(Long id) {
		return workoutRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Workout not dound by id:" +id));
	}
	@Override
	public void deleteWorkout(Long id) {
		if(!workoutRepository.existsById(id)) {
			throw new RuntimeException("Cannot delete - Workout not found with id:" +id);
		}
		workoutRepository.deleteById(id);
	}
	
}

package com.proj.FitLifeTracker.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.proj.FitLifeTracker.entity.Meal;
import com.proj.FitLifeTracker.entity.User;
import com.proj.FitLifeTracker.service.MealService;
import com.proj.FitLifeTracker.repository.UserRepository;

@RestController
@RequestMapping("/meals")
@CrossOrigin(origins = "*")
public class MealController {
	private final MealService mealService;
	private final UserRepository userRepository;
	public MealController(MealService mealService, UserRepository userRepository) {
		this.mealService = mealService;	
		this.userRepository = userRepository;
	}
	@PostMapping("/{userId}")
	public Meal addMeal(@PathVariable Long userId,@RequestBody Meal meal) {
		User user = userRepository.findById(userId)
				.orElseThrow(() ->new RuntimeException("User not found"));
		meal.setUser(user);
		return mealService.addMeal(meal);
	}
	@GetMapping
	public List<Meal> getAllMeals(){
		return mealService.getAllMeals();
	}
	@GetMapping("/user/{userId}")
	public List<Meal> getMealByUser(@PathVariable Long userId) {
		return userRepository.findById(userId)
				.map(user -> user.getMeals())
				.orElseThrow(()-> new RuntimeException("User not found"));
	}
	@DeleteMapping("/{id}")
	public String deleteMeal(@PathVariable Long id) {
		mealService.deleteMeal(id);
		return "Meal deleted successfully with id:" +id;
	}

}

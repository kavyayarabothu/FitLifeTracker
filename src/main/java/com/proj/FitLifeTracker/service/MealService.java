package com.proj.FitLifeTracker.service;
import java.util.List;
import com.proj.FitLifeTracker.entity.Meal;

public interface MealService {
	List<Meal> getAllMeals();
	Meal getMealById(Long id);
	Meal addMeal(Meal meal);
	void deleteMeal(Long id);

}

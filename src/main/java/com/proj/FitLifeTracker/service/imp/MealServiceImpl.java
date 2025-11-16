package com.proj.FitLifeTracker.service.imp;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import com.proj.FitLifeTracker.entity.Meal;
import com.proj.FitLifeTracker.service.MealService;
import com.proj.FitLifeTracker.service.api.SpoonacularService;
import com.proj.FitLifeTracker.repository.MealRepository;

@Service
public class MealServiceImpl implements MealService{
	public final MealRepository mealRepository;
	public final SpoonacularService spoonacularService;
	
	public MealServiceImpl (MealRepository mealRepository, SpoonacularService spoonacularService) {
		this.mealRepository = mealRepository;
		this.spoonacularService = spoonacularService;
	}
	@Override
	public Meal addMeal(Meal meal) {
		int caloriesFromApi = spoonacularService.getCaloriesFromApi(meal.getMealName());
		meal.setCalories(caloriesFromApi);
		meal.setDate(LocalDate.now());
		return mealRepository.save(meal);	
	}
	@Override
	public List<Meal> getAllMeals(){
		return mealRepository.findAll();
	}
	@Override
	public Meal getMealById(Long id) {
		return mealRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Meal Not Found with Id:" +id));
	}
	
	@Override
	public void deleteMeal(Long id) {
		mealRepository.deleteById(id);
	}

}

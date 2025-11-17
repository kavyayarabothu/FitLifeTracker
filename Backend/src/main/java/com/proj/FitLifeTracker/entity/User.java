package com.proj.FitLifeTracker.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int age;
	private String gender;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , orphanRemoval = true)
	@JsonIgnoreProperties("user")
	private List<Meal>meals = new ArrayList<>();
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , orphanRemoval = true)
	@JsonIgnoreProperties("user")
	private List<Workout>workouts = new ArrayList<>();
	
	public double getTotalCaloriesConsumed() {
		return meals.stream().mapToDouble(Meal::getCalories).sum();
		}
	public Double getTotalCaloriesBurned() {
        return workouts.stream().mapToDouble(Workout::getCaloriesBurned).sum();
		}
	public double getCaloriesBalanced() {
		return getTotalCaloriesConsumed() - getTotalCaloriesBurned();
	}
	
	public Long getId(){return id;}
	public void setId(Long id) {this.id = id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}
	public String getGender() {return gender;}
	public void setGender(String gender) {this.gender = gender;}
	
	public List<Meal> getMeals(){return meals;}
	public List<Workout> getWorkouts(){return workouts;}
	
	
}

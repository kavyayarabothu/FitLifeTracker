package com.proj.FitLifeTracker.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "meals")
public class Meal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mealName;
	private String mealType;
	private int calories;
	private LocalDate date;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties("meals")
	private User user;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getMealName() {return mealName;}
	public void setMealName(String mealName) {this.mealName = mealName;}
	public String getMealType() {return mealType;}
	public void setMealType(String mealType) {this.mealType =mealType;}
	public int getCalories() {return calories;}
	public void setCalories(int calories) {this.calories = calories;}
	public LocalDate getDate() {return date;}
	public void setDate(LocalDate date) {this.date = date;}
	public User getUser() {return user;}
	public void setUser(User user) {this.user = user;}
	
	

}

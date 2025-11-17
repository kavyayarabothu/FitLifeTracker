package com.proj.FitLifeTracker.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Workouts")
public class Workout {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String workoutType;
	private int duration;
	private double weight;
	@Column(name = "calories_burned")
	private double caloriesBurned;
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties("workout")
	private User user;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getWorkoutType() {return workoutType;}
	public void setWorkoutType(String workoutType) {this.workoutType = workoutType;}
	public double getWeight() {return weight;}
	public void setWeight(double weight) {this.weight = weight;}
	public int getDuration() {return duration;}
	public void  setDuration(int duration) {this.duration = duration;}
	public double getCaloriesBurned() {return caloriesBurned;}
	public void setCaloriesBurned(double caloriesBurned) {this.caloriesBurned = caloriesBurned;}
	public LocalDate getDate() {return date;}
	public void setDate(LocalDate date) {this.date = date;}
	public User getUser() {return user;}
	public void setUser(User user) {this.user = user;}
	
}

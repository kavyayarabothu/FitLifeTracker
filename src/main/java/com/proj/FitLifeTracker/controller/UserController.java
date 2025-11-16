package com.proj.FitLifeTracker.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.proj.FitLifeTracker.entity.User;
import com.proj.FitLifeTracker.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	@PostMapping
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	@GetMapping
	public List<User> getAllUser(){
		return userService.getAllUsers();
	}
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return "User successfully deleted with id:" +id;
		}

}

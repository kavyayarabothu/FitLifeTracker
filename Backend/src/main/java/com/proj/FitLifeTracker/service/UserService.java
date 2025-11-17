package com.proj.FitLifeTracker.service;
import java.util.List;
import com.proj.FitLifeTracker.entity.User;

public interface UserService {
	List<User> getAllUsers();
	User getUserById(Long id);
	User saveUser(User user);
	void deleteUser(Long id);

}

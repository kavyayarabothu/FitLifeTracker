package com.proj.FitLifeTracker.service.imp;
import java.util.List;
import org.springframework.stereotype.Service;
import com.proj.FitLifeTracker.entity.User;
import com.proj.FitLifeTracker.service.UserService;
import com.proj.FitLifeTracker.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;	
	}
	
	@Override
	public List<User>getAllUsers(){
		return userRepository.findAll();
	}
	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User Not Found"));
	}
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}

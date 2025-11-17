package com.proj.FitLifeTracker.service.imp;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.proj.FitLifeTracker.entity.User;
import com.proj.FitLifeTracker.repository.UserRepository;
import com.proj.FitLifeTracker.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	private final UserRepository userRepository;
	public ReportServiceImpl (UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Override
	public List<String> getAllReports(){
		List<User> users = userRepository.findAll();
		return users.stream()
				.map(user -> user.getName() +
						"|Consumed:" + user.getTotalCaloriesConsumed()+
						"|Burned:" + user.getTotalCaloriesBurned()+
						"|Balanced:" + user.getCaloriesBalanced())
				.collect(Collectors.toList());
	}
	@Override
	public String getUserReport(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(()-> new RuntimeException ("User not found with id:" + id));
				return user.getName()+
						"|Consumed:" + user.getTotalCaloriesConsumed()+
						"|Burned:" + user.getTotalCaloriesBurned()+
						"|Balanced:" + user.getCaloriesBalanced();
	}

}

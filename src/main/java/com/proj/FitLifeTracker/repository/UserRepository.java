package com.proj.FitLifeTracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proj.FitLifeTracker.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

}

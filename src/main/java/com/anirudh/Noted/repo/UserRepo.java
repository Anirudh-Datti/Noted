package com.anirudh.Noted.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anirudh.Noted.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findByUserName(String userName);

    
} 
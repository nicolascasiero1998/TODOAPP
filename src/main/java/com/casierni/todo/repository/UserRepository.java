package com.casierni.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casierni.todo.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}

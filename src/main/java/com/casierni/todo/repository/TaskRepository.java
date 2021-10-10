package com.casierni.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casierni.todo.entity.Task;


public interface TaskRepository extends JpaRepository<Task, Long>{
	List<Task> findAllByUserId(Long userId);
}

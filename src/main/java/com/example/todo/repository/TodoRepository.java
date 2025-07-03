package com.example.todo.repository;

import java.util.*;


import com.example.todo.model.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository  extends JpaRepository<Todo,Long>{
	List<Todo> findByUserId(Long userId);
	

}

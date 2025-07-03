package com.example.todo.controller;


import com.example.todo.*;
import com.example.todo.model.Todo;
import com.example.todo.model.User;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;

//import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:3000") //Allow React frontend to access
public class TodoController {
	
	
	@Autowired
	private TodoRepository repo;
	
	@Autowired
	private UserRepository userRepository;
	
	
	private User getCurrentUser()
	{
		String UserName =SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("Fetching current user from getCUrrentUser: " + UserName);
		return  userRepository.findByUsername(UserName).orElseThrow(()->new RuntimeException("User not found"));
	}
	
	@GetMapping
	public ResponseEntity<List<Todo>> getAllTodos()
	{
		
		User user = getCurrentUser();
		System.out.println("Inside /api/todos");
		List<Todo> todos = repo.findByUserId(user.getId());
		return ResponseEntity.ok(todos);
		
	}
	
	@PostMapping
	public ResponseEntity<Todo> createTodo(@RequestBody Todo todo)
	{
		User user = getCurrentUser();
		
		todo.setUser(user);
		Todo savedTodo = repo.save(todo);
		
		return ResponseEntity.ok(savedTodo);	
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateTodo(@PathVariable Long id,@RequestBody Todo todo)
	{
		User user = getCurrentUser();
		//find the todo from the todo id and match the user_id with the current user 
		//if not equal means different user that todo is of different user which is logged in ;
		
		Todo existing_todo = repo.findById(id).orElseThrow(()->new RuntimeException("Todo not found"));
		
		if(!existing_todo.getUser().getId().equals((todo.getUser().getId())))
		{
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorised");
			
		}
		
		
		existing_todo.setTitle(todo.getTitle());
		existing_todo.setCompleted(todo.isCompleted());
		repo.save(existing_todo);
		return ResponseEntity.ok(existing_todo);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable Long id)
	{
		
		User user = getCurrentUser();
		Todo todo = repo.findById(id).orElseThrow(()->new RuntimeException("Todo not found"));
		if(!todo.getUser().getId().equals(user.getId()))
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorised");
		}
		
		
		repo.deleteById(id);
		return ResponseEntity.ok("delete");
		
	}
	
	
	
	
	

}

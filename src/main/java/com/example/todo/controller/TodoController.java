package com.example.todo.controller;


import com.example.todo.*;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;

import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping
	public List<Todo> getAllTodos()
	{
		return repo.findAll();
	}
	
	@PostMapping
	public Todo createTodo(@RequestBody Todo todo)
	{
		return repo.save(todo);
	}
	
	@PutMapping("/{id}")
	public Todo updateTodo(@PathVariable Long id,@RequestBody Todo todo)
	{
		Todo existing = repo.findById(id).orElseThrow();
		existing.setTitle(todo.getTitle());
		existing.setCompleted(todo.isCompleted());
		return repo.save(existing);
		
	}
	@DeleteMapping("/{id}")
	public void deleteTodo(@PathVariable Long id)
	{
		repo.deleteById(id);
		
	}
	
	
	
	
	

}

// we need to model because this is the blueprnt of our databse
//whatever we have in the databse, same is present here




package com.example.todo.model;

import jakarta.persistence.*;

@Entity
public class Todo{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String title;
	private boolean completed;
	
	
	public Todo() {};
	
	
	public Todo(Long id, String title, boolean completed)
	{
		this.id = id;
		this.title = title;
		this.completed = completed;
	}
	
	
	public Long getId()
	{
		return id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public boolean isCompleted()
	{
		return completed;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public void setTitle(String title)
	{
		this.title =title;
	}
	
	public void setCompleted(boolean completed)
	{
		this.completed =completed;
	}
	
	
	
	
	
}
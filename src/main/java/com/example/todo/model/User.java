package com.example.todo.model;


import jakarta.persistence.*;



@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String username;
	private  String  password;
	
	public Long getId()
	{
		return id;
	}
	
	public  String getUsername()
	{
		
		return username;
		
	}
	
	
	public  String  getPassword()
	{
		return password;
	}
	
	public void setId(Long Id)
	{
		this.id = Id;
		
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setPassword( String password)
	{
		this.password = password;
	}
	

}

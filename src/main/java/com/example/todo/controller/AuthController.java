package com.example.todo.controller;


import com.example.todo.model.User;
import com.example.todo.repository.UserRepository;
import com.example.todo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private UserRepository  userRepository;
	@Autowired private  PasswordEncoder passwordEncoder;
	@Autowired private  JwtUtil jwtUtil;
	
	
	@PostMapping("/register")
	public  ResponseEntity<?> register(@RequestBody User user)
	{
		
		System.out.println("Inside resgister");
		
		
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
		    return ResponseEntity
		            .status(HttpStatus.CONFLICT) // 409 Conflict is more appropriate
		            .body("Username is already taken");
		}
		
		  user.setPassword(passwordEncoder.encode(user.getPassword()));
		  userRepository.save(user);
		  return ResponseEntity.ok("User registered successfully");
		  
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User loginRequest)
	{
		System.out.println("Hiii");
		System.out.println(loginRequest.getUsername());
		System.out.println(loginRequest.getPassword());
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		 String token = jwtUtil.generateToken(loginRequest.getUsername());
		 System.out.println(token);
	        return ResponseEntity.ok(token);
		
	}
	
	
	
	
	
	

}

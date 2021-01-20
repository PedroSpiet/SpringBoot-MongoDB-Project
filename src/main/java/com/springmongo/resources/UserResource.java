package com.springmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springmongo.domain.Posts;
import com.springmongo.domain.User;
import com.springmongo.dtos.UserDTO;
import com.springmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> users = service.findAll();
		List<UserDTO> usersDTO = users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(usersDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findOne(@PathVariable String id) {
		User user = service.findOne(id);
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		User createdUser = service.createUser(user);
		return ResponseEntity.ok().body(createdUser);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) {
		String deletedUser = service.deleteUser(id);
		return ResponseEntity.ok().body(deletedUser);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> updateUser(@Validated @RequestBody User updateUser, @PathVariable String id) {
		updateUser.setId(id);
		User user = service.updateUser(updateUser);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Posts>> findPosts(@PathVariable String id) {
		User posts = service.findOne(id);
		return ResponseEntity.ok().body(posts.getPosts());
	}
}

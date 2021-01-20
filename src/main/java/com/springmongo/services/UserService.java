package com.springmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmongo.domain.User;
import com.springmongo.repositories.UserRepository;
import com.springmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		List<User> users = repository.findAll();
		return users;
	}
	
	public User findOne(String id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("User not found"));
	}
	
	public User createUser(User user) {
		return repository.insert(user);
	}
	
	public String deleteUser(String id) {
		findOne(id);
		repository.deleteById(id);
		return "Deletado com Sucesso!";
	}
}

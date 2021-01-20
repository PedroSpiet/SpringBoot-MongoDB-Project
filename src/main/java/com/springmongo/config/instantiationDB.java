package com.springmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.springmongo.domain.Posts;
import com.springmongo.domain.User;
import com.springmongo.dtos.AuthorDTO;
import com.springmongo.repositories.PostRepository;
import com.springmongo.repositories.UserRepository;

@Configuration
public class instantiationDB implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		User user1 = new User(null, "Maria Brown", "maria@gmail.com");
		User user2 = new User(null, "Alex Green", "alex@gmail.com");
		User user3 = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(user1, user2, user3));
		
		Posts p1 = new Posts(null, sdf.parse("20/01/2021"), "Partiu Viagem", "Vou viajar para SP!", new AuthorDTO(user1));
		Posts p2 = new Posts(null, sdf.parse("20/01/2021"), "Bom dia", "Acordei feliz!", new AuthorDTO(user1));
		
		postRepository.saveAll(Arrays.asList(p1, p2));
	}
	
}

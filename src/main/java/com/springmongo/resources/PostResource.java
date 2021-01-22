package com.springmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springmongo.domain.Posts;
import com.springmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	@Autowired
	private PostService postService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Posts> findPost(@PathVariable String id) { 
		Posts post = postService.findPost(id);
		return ResponseEntity.ok().body(post);
	}
}

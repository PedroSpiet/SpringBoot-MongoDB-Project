package com.springmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springmongo.domain.Posts;

@Repository
public interface PostRepository extends MongoRepository<Posts, String>{

}

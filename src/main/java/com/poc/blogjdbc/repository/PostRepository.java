package com.poc.blogjdbc.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.poc.blogjdbc.model.Post;

public interface PostRepository extends CrudRepository<Post, Integer>
{
	@Query("SELECT * FROM POST WHERE author = :id")
	List<Post> findByAuthor(Integer id);

	// List<Post> findByAuthorLastNameIgnoreCase(String lastName);
}

package com.poc.blogjdbc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.blogjdbc.dto.PostDetails;
import com.poc.blogjdbc.model.Post;
import com.poc.blogjdbc.repository.AuthorRepository;
import com.poc.blogjdbc.repository.PostRepository;

@RestController
@RequestMapping("/api/posts")
public class PostController
{
	private final PostRepository posts;
	private final AuthorRepository authors;

	public PostController(PostRepository postRepository, AuthorRepository authorRepository)
	{
		this.posts = postRepository;
		this.authors = authorRepository;
	}

	@GetMapping
	public Iterable<Post> findAll()
	{
		return posts.findAll();
	}

	@GetMapping("/{id}")
	public Post findById(@PathVariable Integer id)
	{
		return posts.findById(id).orElse(null);
	}

	@GetMapping("/{id}/details")
	public PostDetails getPostDetails(@PathVariable Integer id)
	{
		Post post = posts.findById(id).orElse(null);
		return new PostDetails(post, authors.findById(post.getAuthor().getId()).get());
	}
}

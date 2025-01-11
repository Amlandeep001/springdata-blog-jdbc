package com.poc.blogjdbc.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import com.poc.blogjdbc.model.Author;
import com.poc.blogjdbc.model.Comment;
import com.poc.blogjdbc.model.Post;

@SpringBootTest
class PostRepositoryTest
{
	private final PostRepository posts;
	private final AuthorRepository authorRepository;

	@Autowired
	public PostRepositoryTest(PostRepository posts, AuthorRepository authorRepository)
	{
		this.posts = posts;
		this.authorRepository = authorRepository;
	}

	private AggregateReference<Author, Integer> author;

	@BeforeEach
	void setUp()
	{
		author = AggregateReference.to(authorRepository.save(new Author(null, "Amlandeep", "Nandi", "amlandeep.nandi@gmail.com", "amlandeep")).id());
	}

	@Test
	void shouldSaveValidPost()
	{
		Post post = new Post("TEST", "...", author);
		assertNull(post.getId());
		Post reloaded = posts.save(post);
		assertNotNull(reloaded.getId());
	}

	@Test
	void shouldSaveValidPostWithoutAuthor()
	{
		Post post = new Post("TEST", "...", null);
		assertNull(post.getId());
		Post reloaded = posts.save(post);
		assertNotNull(reloaded.getId());
		assertNull(reloaded.getAuthor());
	}

	@Test
	void shouldPostWithComments()
	{
		Post post = new Post("TEST", "...", null);
		post.addComments(List.of(new Comment("Amlan", "test comment"), new Comment("Amlan", "test comment 2")));
		posts.save(post);

		Post p = posts.findById(post.getId()).orElse(null);
		assertNotNull(p);
		assertNotNull(p.getId());
		assertEquals(2, p.getComments().size());
		assertEquals("Dan", p.getComments().iterator().next().getName());
	}

	@Test
	void shouldPostWithNoCommentsReturns0AndNotNull()
	{
		Post post = new Post("TEST", "...", null);
		posts.save(post);
		Post p = posts.findById(post.getId()).orElse(null);
		assertNotNull(p);
		assertEquals(0, p.getComments().size());
	}

}
package com.poc.blogjdbc.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthorRepositoryTest
{

	private final AuthorRepository authors;

	@Autowired
	public AuthorRepositoryTest(AuthorRepository authors)
	{
		this.authors = authors;
	}

	@Test
	void shouldReturnAllAuthors()
	{
		long count = StreamSupport.stream(authors.findAll().spliterator(), false).count();
		assertEquals(1, count);
	}

}
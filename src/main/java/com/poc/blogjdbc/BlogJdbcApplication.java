package com.poc.blogjdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import com.poc.blogjdbc.model.Author;
import com.poc.blogjdbc.model.Comment;
import com.poc.blogjdbc.model.Post;
import com.poc.blogjdbc.repository.AuthorRepository;
import com.poc.blogjdbc.repository.PostRepository;

@SpringBootApplication
public class BlogJdbcApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(BlogJdbcApplication.class, args);
	}

	@Bean
	CommandLineRunner run(PostRepository postRepository, AuthorRepository authorRepository)
	{
		return args ->
		{
			AggregateReference<Author, Integer> author = AggregateReference.to(authorRepository.save(new Author(null, "Amlandeep", "Nandi", "amlandeep.nandi@gmail.com", "amlandeep")).id());

			Post post = new Post("Amlan's First Post", "This is Amlan's First Post", author);
			post.addComment(new Comment("Amlan", "This is a comment"));
			post.addComment(new Comment("John", "This is another comment"));
			postRepository.save(post);
		};
	}

}

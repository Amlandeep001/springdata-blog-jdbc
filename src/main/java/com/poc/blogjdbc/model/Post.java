package com.poc.blogjdbc.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post
{
	@Id
	@JsonIgnore
	Integer id;
	String title;
	String content;
	LocalDateTime publishedOn;
	LocalDateTime updatedOn;
	Set<Comment> comments = new HashSet<>();
	AggregateReference<Author, Integer> author;

	public Post(String title, String content, AggregateReference<Author, Integer> author)
	{
		this.title = title;
		this.content = content;
		this.author = author;
		this.publishedOn = LocalDateTime.now();
	}

	public void addComments(List<Comment> comments)
	{
		comments.forEach(this::addComment);
	}

	public void addComment(Comment comment)
	{
		comments.add(comment);
		comment.post = this;
	}

}

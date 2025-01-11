package com.poc.blogjdbc.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Transient;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.PackagePrivate;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment
{
	String name;
	String content;
	LocalDateTime publishedOn;
	LocalDateTime updatedOn;

	@Transient
	@PackagePrivate
	Post post;

	public Comment(String name, String content)
	{
		this.name = name;
		this.content = content;
		this.publishedOn = LocalDateTime.now();
	}
}

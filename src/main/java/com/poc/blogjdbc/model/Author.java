package com.poc.blogjdbc.model;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record Author(
		@Id @JsonIgnore Integer id,
		String firstName,
		String lastName,
		String email,
		String username)
{
}

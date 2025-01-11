package com.poc.blogjdbc.repository;

import org.springframework.data.repository.CrudRepository;

import com.poc.blogjdbc.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer>
{
}

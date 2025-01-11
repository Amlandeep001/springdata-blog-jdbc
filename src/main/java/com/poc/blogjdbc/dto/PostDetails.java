package com.poc.blogjdbc.dto;

import com.poc.blogjdbc.model.Author;
import com.poc.blogjdbc.model.Post;

public record PostDetails(Post post, Author author)
{
}

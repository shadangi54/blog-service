package com.shadangi54.blog.entity;


import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@RedisHash("Blog")
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;
	private String author;
	private boolean isDeleted;
}

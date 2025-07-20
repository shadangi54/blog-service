package com.shadangi54.blog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shadangi54.blog.DTO.BlogDTO;
import com.shadangi54.blog.entity.Blog;
import com.shadangi54.blog.manager.BlogManager;

import lombok.AllArgsConstructor;

@RequestMapping("/blog-service")
@RestController
@AllArgsConstructor
public class BlogService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BlogService.class);

	private BlogManager blogManager;

	@GetMapping("/blogs")
	public ResponseEntity<Object> getBlogs() {
		LOGGER.info("Fetching all blogs");
		ResponseEntity<Object> response = null;
		try {
			List<Blog> blogs = blogManager.getBlogs();
			response = ResponseEntity.ok(blogs);
		} catch (Exception e) {
			LOGGER.error("Error fetching blogs", e);
			response = ResponseEntity.status(500).body("Error fetching blogs: " + e.getMessage());
		}

		return response;
	}

	@GetMapping("/blogs/{id}")
	public ResponseEntity<Object> getBlogById(@PathVariable("id")  Long id) {
		LOGGER.info("Fetching blog with ID: {}", id);
		ResponseEntity<Object> response = null;
		try {
			Blog blog = blogManager.getBlogById(id);
			if (blog != null) {
				response = ResponseEntity.ok(blog);
			} else {
				response = ResponseEntity.status(404).body("Blog not found with ID: " + id);
			}
		}catch(Exception e) {
			LOGGER.error("Error fetching blog with ID: {}", id, e);
			response = ResponseEntity.status(500).body("Error fetching blog: " + e.getMessage());
		}
		return response;
	}
	
	@PostMapping("/blogs")
	public ResponseEntity<Object> createBlog(@RequestBody BlogDTO blogDTO) {
		LOGGER.info("Creating new blog: {}", blogDTO);
		ResponseEntity<Object> response = null;
		try {
			Blog createdBlog = blogManager.createBlog(blogDTO);
			response = ResponseEntity.status(201).body(createdBlog);
		} catch (Exception e) {
			LOGGER.error("Error creating blog", e);
			response = ResponseEntity.status(500).body("Error creating blog: " + e.getMessage());
		}
		return response;
	}
	
	@PutMapping("/blogs/{id}")
	public ResponseEntity<Object> updateBlog(@PathVariable Long id, @RequestBody BlogDTO blogDTO) {
		LOGGER.info("Updating blog with ID: {}", id);
		ResponseEntity<Object> response = null;
		try {
			BlogDTO updatedBlog = blogManager.updateBlog(id, blogDTO);
			if (updatedBlog != null) {
				response = ResponseEntity.ok(updatedBlog);
			} else {
				response = ResponseEntity.status(404).body("Blog not found with ID: " + id);
			}
		} catch (Exception e) {
			LOGGER.error("Error updating blog with ID: {}", id, e);
			response = ResponseEntity.status(500).body("Error updating blog: " + e.getMessage());
		}
		return response;
	}
	
	@DeleteMapping("/blogs/{id}")
	public ResponseEntity<Object> deleteBlog(@PathVariable Long id) {
		LOGGER.info("Deleting blog with ID: {}", id);
		ResponseEntity<Object> response = null;
		try {
			boolean isDeleted = blogManager.deleteBlog(id);
			if (isDeleted) {
				response = ResponseEntity.ok("Blog deleted successfully");
			} else {
				response = ResponseEntity.status(404).body("Blog not found with ID: " + id);
			}
		} catch (Exception e) {
			LOGGER.error("Error deleting blog with ID: {}", id, e);
			response = ResponseEntity.status(500).body("Error deleting blog: " + e.getMessage());
		}
		return response;
	}

}

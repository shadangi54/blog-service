package com.shadangi54.blog.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shadangi54.blog.DTO.BlogDTO;
import com.shadangi54.blog.entity.Blog;
import com.shadangi54.blog.repository.BlogRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BlogManager.class);
	
	private BlogRepository blogRepository;
	
	public List<Blog> getBlogs() {
		List<Blog> blogs = (List<Blog>) blogRepository.findAll();
		blogs.removeIf(Blog::isDeleted);
		return blogs;
	}

	public Blog getBlogById(Long id) {
		Blog blog = blogRepository.findById(id).orElse(null);
		if (blog != null) {
			return blog;
		}
		return null;
	}

	public Blog createBlog(BlogDTO blogDTO) {
		LOGGER.info("Creating new blog with title: {}", blogDTO.getTitle());
		Blog blog = new Blog();
		blog.setTitle(blogDTO.getTitle());
		blog.setContent(blogDTO.getContent());
		blog.setAuthor(blogDTO.getAuthor());
		blog.setDeleted(false);
	
		blog = blogRepository.save(blog);
		LOGGER.info("Blog created with ID: {}", blog.getId());
		return blog;
		
	}

	public BlogDTO updateBlog(Long id, BlogDTO blogDTO) {
		LOGGER.info("Updating blog with ID: {}", id);
		Blog existingBlog = blogRepository.findById(id).orElse(null);
		if (existingBlog != null) {
			existingBlog.setTitle(blogDTO.getTitle());
			existingBlog.setContent(blogDTO.getContent());
			existingBlog.setAuthor(blogDTO.getAuthor());
			existingBlog = blogRepository.save(existingBlog);
			LOGGER.info("Blog updated with ID: {}", existingBlog.getId());
			
			blogDTO = new BlogDTO();
			blogDTO.setId(existingBlog.getId());
			blogDTO.setTitle(existingBlog.getTitle());
			blogDTO.setContent(existingBlog.getContent());
			blogDTO.setAuthor(existingBlog.getAuthor());
			return blogDTO;
		}
		return null;
	}

	public boolean deleteBlog(Long id) {
		LOGGER.info("Deleting blog with ID: {}", id);
		Blog existingBlog = blogRepository.findById(id).orElse(null);
		if (existingBlog != null) {
			existingBlog.setDeleted(true); // Assuming you have a method to mark as deleted
			blogRepository.save(existingBlog);
			LOGGER.info("Blog marked as deleted with ID: {}", existingBlog.getId());
			return true;
		}
		return false;
	}
	
	
}

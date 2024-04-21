package com.project.professor.allocation.btf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.btf.repository.CourseRepository;
import com.project.professor.allocation.btf.entity.Course;

@Service
public class CourseService {

	private final CourseRepository courseRepository;
	
	public CourseService(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}
	
	public List<Course> findAll(String name) {
		return name == null ? courseRepository.findAll() : courseRepository.findByNameIgnoreCaseContaining(name);
	}
	
	public Course findById(Long id) {
		return courseRepository.findById(id).orElse(null);
	}
	
	public Course create(Course course) {
		course.setId(null);
		
		return saveInternal(course);
	}
	
	public Course update(Course course) {
		return courseRepository.existsById(course.getId()) ? saveInternal(course) : null;
	}
	
	private Course saveInternal(Course course) {
		return courseRepository.save(course);
	}
	
	public boolean deleteById(Long id) {
		boolean exist = courseRepository.existsById(id);
		if (exist) {
			courseRepository.deleteById(id);
		}
		return exist;
	}
	
	public void deleteAll() {
		courseRepository.deleteAllInBatch();
	}
}

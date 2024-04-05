package com.project.professor.allocation.btf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.professor.allocation.btf.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	List<Course> findByNameIgnoreCaseContaining(String name);
}


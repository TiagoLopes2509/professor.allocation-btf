package com.project.professor.allocation.btf.repository;

import com.project.professor.allocation.btf.entity.Allocation;
import com.project.professor.allocation.btf.entity.Course;
import com.project.professor.allocation.btf.entity.Professor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {

	List<Allocation> findByProfessor(Professor professor);
	
	List<Allocation> findByCourse(Course course);
}

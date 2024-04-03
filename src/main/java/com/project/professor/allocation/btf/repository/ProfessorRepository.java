package com.project.professor.allocation.btf.repository;

import com.project.professor.allocation.btf.entity.Department;
import com.project.professor.allocation.btf.entity.Professor;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	List<Professor> findByDepartment(Department department);
	
	List<Professor> findByNameContainingIgnoreCase(String name);
}


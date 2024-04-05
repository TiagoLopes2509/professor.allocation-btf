package com.project.professor.allocation.btf.repository;

import com.project.professor.allocation.btf.entity.Department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	List<Department> findByNameIgnoreCaseContaining(String name);
}


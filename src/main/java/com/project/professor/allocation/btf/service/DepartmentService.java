package com.project.professor.allocation.btf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.btf.repository.DepartmentRepository;
import com.project.professor.allocation.btf.entity.Department;

@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;
	
	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}
	
	public List<Department> findAll(String name) {
		return name == null ? departmentRepository.findAll() : departmentRepository.findByNameIgnoreCaseContaining(name);
	}
	
	public Department findById(Long id) {
		return departmentRepository.findById(id).orElse(null);
	}
	
	public Department create(Department department) {
		department.setId(null);
		
		return saveInternal(department);
	}
	
	public Department update(Department department) {
		return departmentRepository.existsById(department.getId()) ? saveInternal(department) : null;
	}
	
	private Department saveInternal(Department department) {
		return departmentRepository.save(department);
	}
	
	public boolean delete(Department department) {
		boolean exist = departmentRepository.existsById(department.getId());
		if (exist) {
			departmentRepository.delete(department);
		}
		return exist;
	}
	
	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}
}

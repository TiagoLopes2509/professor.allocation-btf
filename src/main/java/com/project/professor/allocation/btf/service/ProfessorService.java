package com.project.professor.allocation.btf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.btf.entity.Professor;
import com.project.professor.allocation.btf.repository.ProfessorRepository;



@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final DepartmentService departmentService;

	public ProfessorService(ProfessorRepository professorRepository, DepartmentService departmentService) {
		super();
		this.professorRepository = professorRepository;
		this.departmentService = departmentService;
	}

	public List<Professor> findAll(String name) {
		
		return name == null ? professorRepository.findAll() : professorRepository.findByNameContainingIgnoreCase(name);

	}
	
	public Professor findById(Long id) {
		return professorRepository.findById(id).orElse(null);
	}
	
	public Professor create(Professor professor) {
		professor.setId(null);
				
		return saveInternal(professor);
	}
	
	public Professor update(Professor professor) {
		return professorRepository.existsById(professor.getId()) ? saveInternal(professor) : null;
	}

	public void deleteById(Long id) {
		if (professorRepository.existsById(id))
			professorRepository.deleteById(id);	
	}
	
	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}
	
	//----------------------------------------------------------------------------------------------------------------------//
	
	private Professor saveInternal(Professor professor) {
		Professor prof = professorRepository.save(professor);
		
		prof.setDepartment(departmentService.findById(prof.getDepartment().getId()));
		
		return prof;
	}
}

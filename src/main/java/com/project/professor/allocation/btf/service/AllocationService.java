package com.project.professor.allocation.btf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.btf.entity.Allocation;
import com.project.professor.allocation.btf.entity.Course;
import com.project.professor.allocation.btf.entity.Professor;
import com.project.professor.allocation.btf.repository.AllocationRepository;


@Service
public class AllocationService {

	private final AllocationRepository allocationRepository;
	private final ProfessorService professorService;
	private final CourseService courseService;
	
	public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService, CourseService courseService) 
	{
		super();
		this.allocationRepository = allocationRepository;
        this.professorService = professorService;
        this.courseService = courseService;
	}
	
	public List<Allocation> findAll(){
		return allocationRepository.findAll();
	}
	
	public Allocation findById(Long id) {
		return allocationRepository.findById(id).orElse(null);
	}
	
	public Allocation create(Allocation allocation) {
		return saveInternal(allocation);
	}
	
	public Allocation update(Allocation allocation) {
		return allocationRepository.existsById(allocation.getId()) == true ?  saveInternal(allocation) : null;
	}
	
	public void deleteById(Long id) {
		if(allocationRepository.existsById(id))
			allocationRepository.deleteById(id);
	}
	
	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}

	//----------------------------------------------------------------------------------------------------------------------//
	
	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		return !currentAllocation.getId().equals(newAllocation.getId()) 
				&& currentAllocation.getDay() == newAllocation.getDay()
				&& currentAllocation.getStartHour().compareTo(newAllocation.getEndHour()) < 0
				&& newAllocation.getStartHour().compareTo(currentAllocation.getEndHour())> 0;
	}
	
	private Allocation saveInternal(Allocation allocation) {
		if(!isEndHourGreaterThanStartHour(allocation) || hasCollision(allocation)) {
			throw new RuntimeException();
		} else {
			allocation = allocationRepository.save(allocation);
			
			Professor professor = professorService.findById(allocation.getProfessor().getId());
			allocation.setProfessor(professor);
			
			Course course = courseService.findById(allocation.getCourse().getId());
			allocation.setCourse(course);
			
			return allocation;
		}
	}
	
	boolean isEndHourGreaterThanStartHour(Allocation allocation) {
		return allocation != null && allocation.getStartHour() != null && allocation.getEndHour() 
				!= null && allocation.getEndHour().compareTo(allocation.getStartHour()) > 0;
	}
	
	boolean hasCollision(Allocation newAllocation) {
		boolean hasCollision = false;
		
		List<Allocation> currentAllocations = allocationRepository.findByProfessor(newAllocation.getProfessor());
		
		for(Allocation cuureAllocation : currentAllocations) {
			hasCollision = hasCollision(cuureAllocation, newAllocation);
			
			if(hasCollision) {
				break;
			}
		}
		
		return hasCollision;
	}
}

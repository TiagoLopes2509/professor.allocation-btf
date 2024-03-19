package com.project.professor.allocation.btf;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.btf.entity.Allocation;
import com.project.professor.allocation.btf.entity.Course;
import com.project.professor.allocation.btf.entity.Professor;
import com.project.professor.allocation.btf.repository.AllocationRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {

	@Autowired
	AllocationRepository allocationRepository;
	
	@Test
	public void createTest() {
		Allocation alloc = new Allocation();
		alloc.setDay(DayOfWeek.MONDAY);
		alloc.setStartHour(Time.valueOf(LocalTime.of(9, 0)));
		alloc.setEndHour(Time.valueOf(LocalTime.of(11, 0)));
		
		Course course = new Course();
		course.setId(1L);
		
		Professor prof = new Professor();
		prof.setId(1L);
		
		alloc.setCourse(course);
		alloc.setProfessor(prof);
		
		allocationRepository.save(alloc);
		
		System.out.println(alloc);
	}
	
	@Test
	public void updateTest() {
		Allocation alloc = allocationRepository.findById(1L).orElse(null);
		
		if (alloc != null) {
			alloc.setDay(DayOfWeek.TUESDAY);
			alloc.setStartHour(Time.valueOf(LocalTime.of(10, 0)));
			
			allocationRepository.save(alloc);
			
			System.out.println(alloc);
		}
		else {
			System.out.println("Allocation not found");
		}
	}
	
	@Test
	public void readAllTest() {
		List<Allocation> all = allocationRepository.findAll();
		
		if (all != null) {
			System.out.println(all);
		}
		else {
			System.out.println("Allocation not found");
		}
	}
	
	@Test
	public void readTest() {
		Allocation alloc = allocationRepository.findById(10L).orElse(null);
		
		if (alloc != null) {
			System.out.println(alloc);
		}
		else {
			System.out.println("Allocation not found");
		}
	}
	
	@Test
	public void deleteTest() {
		Allocation alloc = allocationRepository.findById(10L).orElse(null);
		
		if (alloc != null) {
			allocationRepository.delete(alloc);
			System.out.println("Allocation deleted");
		}
		else {
			System.out.println("Allocation not found");
		}
	}
}

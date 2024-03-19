package com.project.professor.allocation.btf;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.btf.entity.Department;
import com.project.professor.allocation.btf.entity.Professor;
import com.project.professor.allocation.btf.repository.ProfessorRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {
	
	@Autowired
	public ProfessorRepository professorRepository;

	@Test
	public void createTest() {
		Professor prof = new Professor();
		prof.setName("Tiago 1213");
		prof.setCpf("36196617079");
		
		Department dp = new Department();
		dp.setName("Depto História");
		dp.setId(1L);
		prof.setDepartment(dp);
		
		Professor profTest = professorRepository.save(prof);
		
		System.out.println(profTest);
	}
	
	@Test
	public void updateTest() {
		Professor prof = professorRepository.findById(2L).orElse(null);
		
		if (prof != null) {
			prof.setName("Bruno Leo");
			prof.setCpf("92548234061");
			
			Professor profTest = professorRepository.save(prof);
			
			System.out.println(profTest);
		}
		else {
			System.out.println("Professor not found");
		}
	}
	
	@Test
	public void readAllTest() {
		List<Professor> prof = professorRepository.findAll();
		
		System.out.println(prof);
	}
	
	@Test
	public void readTest() {
		Professor prof = professorRepository.findById(10L).orElse(null);
		
		if (prof != null) {
			System.out.println(prof);
		}
		else {
			System.out.println("Professor not found");
		}
	}
	
	@Test
	public void deleteTest() {
		Professor prof = professorRepository.findById(10L).orElse(null);
		
		if (prof != null) {
			professorRepository.delete(prof);
			System.out.println("Professor deleted");
		}
		else {
			System.out.println("Professor not found");
		}
	}
}

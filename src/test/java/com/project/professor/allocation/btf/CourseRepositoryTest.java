package com.project.professor.allocation.btf;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.btf.entity.Course;
import com.project.professor.allocation.btf.repository.CourseRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseRepositoryTest {

	@Autowired
	public CourseRepository courseRepository;
	
	@Test
	public void createTest() {
		Course course = new Course();
		course.setName("Arquitetura de Software Back-End");
		
		Course cTest = courseRepository.save(course);
		
		System.out.println(cTest);
	}
	
	@Test
	public void updateTest() {
		Course course = new Course();
		course.setId(1L);
		course.setName("Arquitetura de Software Front-End");
		
		Course cTest = courseRepository.save(course);
		
		System.out.println(cTest);
	}
	
	@Test
	public void readTest() {
		List<Course> dp = courseRepository.findAll();
		
		System.out.println(dp);
	}
	
	@Test
	public void readOneTest() {
		Optional<Course> course = courseRepository.findById(2L);
		
		if (!course.isEmpty()) {
			System.out.println(course);
		}
		else {
			System.out.println("Course not found");
		}
	}
	
	
	@Test
	public void deleteTest() {
		Optional<Course> course = courseRepository.findById(2L);
		
		if (!course.isEmpty()) {
			courseRepository.deleteById(course.get().getId());
			System.out.println("Course deleted");
		}
		else {
			System.out.println("Course not found");
		}
	}
}

package com.project.professor.allocation.btf.entity;

import java.sql.Time;
import java.time.DayOfWeek;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "allocation")
public class Allocation {
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "dayOfWeek", nullable = false)
	private DayOfWeek day;
	
	@Schema(example = "19:00:00", type = "string")
	@Column(name = "startHour", nullable = false)
	private Time startHour;
	
	@Schema(example = "20:00:00", type = "string")
	@Column(name = "endHour", nullable = false)
	private Time endHour;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "professor_id", nullable = false)
	private Professor professor;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public DayOfWeek getDay() 
	{
		return day;
	}

	public void setDay(DayOfWeek day) 
	{
		this.day = day;
	}

	public Time getStartHour() 
	{
		return startHour;
	}

	public void setStartHour(Time startHour) 
	{
		this.startHour = startHour;
	}

	public Time getEndHour() 
	{
		return endHour;
	}

	public void setEndHour(Time endHour) 
	{
		this.endHour = endHour;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	public void setProfessorId(Long id) {
		Professor professor = new Professor();
		professor.setId(id);
		this.setProfessor(professor);
	}

	public void setCourseId(Long id) {
		Course course = new Course();
		course.setId(id);
		this.setCourse(course);
	}

	@Override
	public String toString() {
		return "Allocation [id=" + id + ", day=" + day + ", startHour=" + startHour + ", endHour=" + endHour
				+ ", professor=" + professor + ", course=" + course + "]";
	}

	

}

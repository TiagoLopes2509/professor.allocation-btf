package com.project.professor.allocation.btf.entity;

import java.sql.Time;
import java.time.DayOfWeek;

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
	
	public Allocation() 
	{
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "day", nullable = false)
	private DayOfWeek day;
	
	@Column(name = "start", nullable = false)
	private Time startHour;
	
	@Column(name = "end", nullable = false)
	private Time endHour;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "professor_id", nullable = false)
	private Professor professor;
	
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

	@Override
	public String toString() 
	{
		return "Allocation [id=" + id + ", day=" + day + ", start=" + startHour + ", end=" + endHour + "]";
	}
	
	

}

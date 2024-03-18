package com.project.professor.allocation.btf.repository;

import com.project.professor.allocation.btf.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}


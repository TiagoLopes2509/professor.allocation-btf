package com.project.professor.allocation.btf.repository;

import com.project.professor.allocation.btf.entity.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {

}

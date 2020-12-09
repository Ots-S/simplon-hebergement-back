package com.simplon.simplontest.repository;

import com.simplon.simplontest.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRespository extends JpaRepository<Project, Long> {
}

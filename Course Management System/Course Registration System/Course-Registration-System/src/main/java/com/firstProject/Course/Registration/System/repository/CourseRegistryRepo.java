package com.firstProject.Course.Registration.System.repository;

import com.firstProject.Course.Registration.System.model.CourseRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRegistryRepo extends JpaRepository<CourseRegistry, Integer> {
}

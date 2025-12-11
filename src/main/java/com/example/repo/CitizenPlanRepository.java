package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.CitizenPlan;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Integer>{
	@Query("SELECT distinct(planName) FROM CitizenPlan ")
    List<String> getPlanNames();
	
	@Query("SELECT distinct(planStatus) FROM CitizenPlan ")
    List<String> getPlanStatus();


}

package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.CitizenPlan;
import com.example.repo.CitizenPlanRepository;
import com.example.request.SearchRequest;

@Service
public class ReportServicelmpl implements ReportService {

    @Autowired
    private CitizenPlanRepository planRepo;

	@Override
	public List<String> getPlanNames() {
		
		return planRepo.getPlanNames();
	
	}

	@Override
	public List<String> getPlanStatuses() {
		
		return planRepo.getPlanStatus();
		
	}
	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		
		return planRepo.findAll();
	}

	@Override
	public boolean exportExcel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exportPdf() {
		// TODO Auto-generated method stub
		return false;
	}

}

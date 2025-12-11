package com.example.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.entity.CitizenPlan;
import com.example.repo.CitizenPlanRepository;
import com.example.request.SearchRequest;

import jakarta.persistence.Entity;

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
		
		CitizenPlan entity=new CitizenPlan();
		
		if(null!=request.getPlanName() && ! "".equals(request.getPlanName()))
		{
			entity.setPlanName(request.getPlanName());
		}
		if(null!=request.getPlanstatus() && ! "".equals(request.getPlanstatus()))
		{
			entity.setPlanStatus(request.getPlanstatus());
		}
		if(null!=request.getGender() && ! "".equals(request.getGender()))
		{
			entity.setGender(request.getGender());
		}
		if(null!=request.getStartDate() && !"".equals(request.getStartDate()))
		{
		String startDate=request.getStartDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate date = LocalDate.parse(startDate, formatter);
		entity.setPlanStartDate(date);
		}
		if(null!=request.getEndDate() && !"".equals(request.getEndDate()))
		{
		String endDate=request.getEndDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate date = LocalDate.parse(endDate, formatter);
		entity.setPlanStartDate(date);
		}
		
		return planRepo.findAll(Example.of(entity));
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

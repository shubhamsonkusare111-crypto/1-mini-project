package com.example.service;

import java.io.File;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.entity.CitizenPlan;
import com.example.repo.CitizenPlanRepository;
import com.example.request.SearchRequest;
import com.example.util.EmailUtils;
import com.example.util.ExcelGenerator;
import com.example.util.PdfGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServicelmpl implements ReportService {

	@Autowired
	private CitizenPlanRepository planRepo;

	@Autowired
	private PdfGenerator pdfgenerator;
	
	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private ExcelGenerator excelGenerator;

	

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

		CitizenPlan entity = new CitizenPlan();

		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if (null != request.getPlanstatus() && !"".equals(request.getPlanstatus())) {
			entity.setPlanStatus(request.getPlanstatus());
		}
		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		if (null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			LocalDate date = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(date);
		}
		if (null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			LocalDate date = LocalDate.parse(endDate, formatter);
			entity.setPlanStartDate(date);
		}

		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {

		 File f=new File("Plans.xls");
	    List<CitizenPlan> plans = planRepo.findAll();
	    excelGenerator.generate(response, plans, f);
	    String subject = "Test mail subject";
	    String body = "<h1> Test mail body</h1>";
	    String to = "shubhamsonkusare0803@gmail.com";
	    emailUtils.sendEmail(subject, body, to,f);
	    f.delete();

	    return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {

	    File f = new File("Plans.pdf");
	    List<CitizenPlan> plans = planRepo.findAll();

	    // 1️⃣ FIRST generate PDF file
	    pdfgenerator.generator(response, plans, f);

	    // 2️⃣ THEN send email
	    String subject = "Test mail subject";
	    String body = "<h1> Test mail body</h1>";
	    String to = "shubhamsonkusare0803@gmail.com";
	    emailUtils.sendEmail(subject, body, to, f);

	    // 3️⃣ Delete file after sending
	    f.delete();

	    return true;
	}
}

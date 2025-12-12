package com.example.service;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; // For XLS (HSSFWorkbook)
import org.apache.poi.ss.usermodel.Workbook; // Common interface for HSSFWorkbook/XSSFWorkbook
import org.apache.poi.ss.usermodel.Sheet; // Represents a sheet
import org.apache.poi.ss.usermodel.Row; // Represents a row in a sheet
import org.apache.poi.ss.usermodel.Cell; // Represents a cell in a row

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.entity.CitizenPlan;
import com.example.repo.CitizenPlanRepository;
import com.example.request.SearchRequest;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

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
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("plans-data");

		// Create headers
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benefit Amt");

		// Fetch data
		List<CitizenPlan> records = planRepo.findAll();
		int dataRowIndex = 1;
		for (CitizenPlan plan : records) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());

			if (null != plan.getPlanStartDate()) {
				dataRow.createCell(4).setCellValue(plan.getPlanStartDate() + "");
			} else {
				dataRow.createCell(4).setCellValue("N/A");
			}
			if (null != plan.getPlanEndDate()) {
				dataRow.createCell(5).setCellValue(plan.getPlanEndDate() + "");
			} else {
				dataRow.createCell(5).setCellValue("N/A");
			}

			if (null != plan.getBenefitAmt()) {
				dataRow.createCell(6).setCellValue(plan.getBenefitAmt());
			} else {
				dataRow.createCell(6).setCellValue("N/A");
			}
			dataRowIndex++;
		}

		// Write to response
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		return true; // Indicate success
	}

	public boolean exportPdf(HttpServletResponse response) throws Exception {

	    response.setContentType("application/pdf");
	    response.setHeader("Content-Disposition", "attachment; filename=plans.pdf");

	    Document document = new Document(PageSize.A4);
	    PdfWriter.getInstance(document, response.getOutputStream());

	    document.open();  // ✔ OPEN FIRST

	

	    Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);

	    Paragraph p = new Paragraph("Citizen Plans Info", titleFont);
	    p.setAlignment(Paragraph.ALIGN_CENTER);
	    p.setSpacingAfter(10f);


	    
	    document.add(p);  // ✔ Add content after open()

	    PdfPTable table = new PdfPTable(6);
	    table.addCell("ID");
	    table.addCell("Citizen Name");
	    table.addCell("Plan Name");
	    table.addCell("Plan Status");
	    table.addCell("Start Date");
	    table.addCell("End Date");
	    
	    List<CitizenPlan> plans = planRepo.findAll();

	    // Loop and add rows
	    for (CitizenPlan plan : plans) {
	        table.addCell(String.valueOf(plan.getCitizenId()));
	        table.addCell(plan.getCitizenName());
	        table.addCell(plan.getPlanName());
	        table.addCell(plan.getPlanStatus());
	        table.addCell(plan.getPlanStartDate()+"");
	        table.addCell(plan.getPlanEndDate()+"");
	    }

	    document.add(table); // ✔ Add table after open()

	    document.close(); // ✔ Always close document

	    return true;
	}

}
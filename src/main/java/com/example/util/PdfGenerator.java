package com.example.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator
{
	public void generator(HttpServletResponse response, List<CitizenPlan> plans,File f) throws Exception {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=plans.pdf");

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document,new FileOutputStream(f));

		document.open(); // ✔ OPEN FIRST

		Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);

		Paragraph p = new Paragraph("Citizen Plans Info", titleFont);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		p.setSpacingAfter(10f);

		document.add(p); // ✔ Add content after open()

		PdfPTable table = new PdfPTable(6);
		table.addCell("ID");
		table.addCell("Citizen Name");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");

		// Loop and add rows
		for (CitizenPlan plan : plans) {
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			table.addCell(plan.getPlanStartDate() + "");
			table.addCell(plan.getPlanEndDate() + "");
		}

		document.add(table); // ✔ Add table after open()

		document.close(); // ✔ Always close document

	}
}

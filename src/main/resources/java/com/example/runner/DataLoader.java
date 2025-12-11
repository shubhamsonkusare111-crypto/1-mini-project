package com.example.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.entity.CitizenPlan;
import com.example.repo.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner{
	
	@Autowired
	private CitizenPlanRepository repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
	    repo.deleteAll();

	    // 1
	    CitizenPlan c1 = new CitizenPlan();
	    c1.setCitizenName("Rahul");
	    c1.setGender("Male");
	    c1.setPlanName("Cash Plan");
	    c1.setPlanStatus("Approved");
	    c1.setPlanStartDate(LocalDate.now());
	    c1.setPlanEndDate(LocalDate.now().plusMonths(6));
	    c1.setBenefitAmt(5000.0);

	    // 2
	    CitizenPlan c2 = new CitizenPlan();
	    c2.setCitizenName("Priya");
	    c2.setGender("Female");
	    c2.setPlanName("Health Plan");
	    c2.setPlanStatus("Approved");
	    c2.setPlanStartDate(LocalDate.now());
	    c2.setPlanEndDate(LocalDate.now().plusMonths(12));
	    c2.setBenefitAmt(7000.0);

	    // 3
	    CitizenPlan c3 = new CitizenPlan();
	    c3.setCitizenName("Amit");
	    c3.setGender("Male");
	    c3.setPlanName("Education Plan");
	    c3.setPlanStatus("Denied");
	    c3.setDenialReason("Income Above Limit");

	    // 4
	    CitizenPlan c4 = new CitizenPlan();
	    c4.setCitizenName("Neha");
	    c4.setGender("Female");
	    c4.setPlanName("Cash Plan");
	    c4.setPlanStatus("Denied");
	    c4.setDenialReason("Documents Missing");

	    // 5
	    CitizenPlan c5 = new CitizenPlan();
	    c5.setCitizenName("Sumit");
	    c5.setGender("Male");
	    c5.setPlanName("Child Plan");
	    c5.setPlanStatus("Terminated");
	    c5.setPlanStartDate(LocalDate.now().minusMonths(8));
	    c5.setTerminatedDate(LocalDate.now());
	    c5.setTerminationRsn("Job Change");

	    // 6
	    CitizenPlan c6 = new CitizenPlan();
	    c6.setCitizenName("Nisha");
	    c6.setGender("Female");
	    c6.setPlanName("Health Plan");
	    c6.setPlanStatus("Terminated");
	    c6.setPlanStartDate(LocalDate.now().minusMonths(4));
	    c6.setTerminatedDate(LocalDate.now());
	    c6.setTerminationRsn("Moved to Another City");

	    // 7
	    CitizenPlan c7 = new CitizenPlan();
	    c7.setCitizenName("Vikas");
	    c7.setGender("Male");
	    c7.setPlanName("Old Age Plan");
	    c7.setPlanStatus("Approved");
	    c7.setPlanStartDate(LocalDate.now());
	    c7.setPlanEndDate(LocalDate.now().plusMonths(5));
	    c7.setBenefitAmt(3000.0);

	    // 8
	    CitizenPlan c8 = new CitizenPlan();
	    c8.setCitizenName("Anjali");
	    c8.setGender("Female");
	    c8.setPlanName("Widow Plan");
	    c8.setPlanStatus("Approved");
	    c8.setPlanStartDate(LocalDate.now());
	    c8.setPlanEndDate(LocalDate.now().plusMonths(6));
	    c8.setBenefitAmt(9000.0);

	    // 9
	    CitizenPlan c9 = new CitizenPlan();
	    c9.setCitizenName("Karan");
	    c9.setGender("Male");
	    c9.setPlanName("Cash Plan");
	    c9.setPlanStatus("Denied");
	    c9.setDenialReason("Age Not Eligible");

	    // 10
	    CitizenPlan c10 = new CitizenPlan();
	    c10.setCitizenName("Sneha");
	    c10.setGender("Female");
	    c10.setPlanName("Health Plan");
	    c10.setPlanStatus("Denied");
	    c10.setDenialReason("Incomplete Application");

	    // 11
	    CitizenPlan c11 = new CitizenPlan();
	    c11.setCitizenName("Rohit");
	    c11.setGender("Male");
	    c11.setPlanName("Child Plan");
	    c11.setPlanStatus("Approved");
	    c11.setPlanStartDate(LocalDate.now());
	    c11.setPlanEndDate(LocalDate.now().plusMonths(10));
	    c11.setBenefitAmt(4500.0);

	    // 12
	    CitizenPlan c12 = new CitizenPlan();
	    c12.setCitizenName("Kavita");
	    c12.setGender("Female");
	    c12.setPlanName("Education Plan");
	    c12.setPlanStatus("Terminated");
	    c12.setPlanStartDate(LocalDate.now().minusMonths(5));
	    c12.setTerminatedDate(LocalDate.now());
	    c12.setTerminationRsn("Personal Reason");

	    List<CitizenPlan> list = Arrays.asList(
	            c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12 );
	    repo.saveAll(list);
	}

}

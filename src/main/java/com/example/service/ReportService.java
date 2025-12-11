package com.example.service;

import java.util.List;

import com.example.entity.CitizenPlan;
import com.example.request.SearchRequest;

public interface ReportService {

    public List<String> getPlanNames();

    public List<String> getPlanStatuses();

    public List<CitizenPlan> search(SearchRequest request); // FIXED

    public boolean exportExcel();

    public boolean exportPdf();
}

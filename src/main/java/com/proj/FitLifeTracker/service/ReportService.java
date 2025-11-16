package com.proj.FitLifeTracker.service;
import java.util.List;

public interface ReportService {
	List<String> getAllReports();
	String getUserReport(Long id);

}

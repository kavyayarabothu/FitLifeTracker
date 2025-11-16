package com.proj.FitLifeTracker.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.proj.FitLifeTracker.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {
	private final ReportService reportService;
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}
	@GetMapping
	public List<String> getAllReports(){
		return reportService.getAllReports();
	}
	@GetMapping("/{id}")
	public String getUserReport(@PathVariable Long id) {
		return reportService.getUserReport(id);
	}

}

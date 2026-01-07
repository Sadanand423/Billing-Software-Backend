package com.billing.controller;

import org.springframework.web.bind.annotation.*;

import com.billing.dto.DashboardSummaryDTO;
import com.billing.service.DashboardService;

@RestController
@RequestMapping("/api/billing/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public DashboardSummaryDTO getSummary() {
        return dashboardService.getSummary();
    }
}

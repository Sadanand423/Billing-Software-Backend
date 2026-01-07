package com.billing.service.Impl;

import org.springframework.stereotype.Service;

import com.billing.dto.DashboardSummaryDTO;
import com.billing.repository.InvoiceRepository;
import com.billing.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final InvoiceRepository invoiceRepository;

    public DashboardServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public DashboardSummaryDTO getSummary() {
        return invoiceRepository.getDashboardSummary();
    }
}

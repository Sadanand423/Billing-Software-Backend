package com.billing.dto;

public class DashboardSummaryDTO {

    private long totalInvoices;
    private double totalSales;
    private double totalAdvance;
    private double totalBalance;

    public DashboardSummaryDTO(long totalInvoices,
                               double totalSales,
                               double totalAdvance,
                               double totalBalance) {
        this.totalInvoices = totalInvoices;
        this.totalSales = totalSales;
        this.totalAdvance = totalAdvance;
        this.totalBalance = totalBalance;
    }

    public long getTotalInvoices() { return totalInvoices; }
    public double getTotalSales() { return totalSales; }
    public double getTotalAdvance() { return totalAdvance; }
    public double getTotalBalance() { return totalBalance; }
}

package com.billing.repository;

import com.billing.dto.DashboardSummaryDTO;
import com.billing.entity.Invoice;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	@Query("""
	        SELECT new com.billing.dto.DashboardSummaryDTO(
	            COUNT(i),
	            COALESCE(SUM(i.totalAmount),0),
	            COALESCE(SUM(i.advanceAmount),0),
	            COALESCE(SUM(i.balanceAmount),0)
	        )
	        FROM Invoice i
	    """)
	    DashboardSummaryDTO getDashboardSummary();
}

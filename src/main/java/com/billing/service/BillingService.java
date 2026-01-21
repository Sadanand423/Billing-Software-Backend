package com.billing.service;

import java.util.List;

import com.billing.dto.InvoiceDTO;
import com.billing.entity.*;

public interface BillingService {

    // ---------------- COMPANY ----------------
    Company saveCompany(Company company);
    Company getCompanyById(Long id);

    // ---------------- CUSTOMER ----------------
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
    List<Customer> getAllCustomers();

    // ---------------- PRODUCT ----------------
    Product saveProduct(Product product);
    List<Product> getAllProducts();

    // ---------------- INVOICE ----------------
    Invoice saveInvoice(Invoice invoice);
    Invoice updateInvoice(Long id, Invoice invoice);
    Invoice getInvoiceById(Long id);
    List<Invoice> getAllInvoices();
    void deleteInvoice(Long id); // <-- delete method added

    // ---------------- PAYMENT ----------------
    Payment savePayment(Payment payment);
	Invoice saveInvoice(InvoiceDTO invoiceDTO);
	Invoice updateInvoice(Long id, InvoiceDTO invoiceDTO);
}

package com.billing.service.Impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.entity.*;
import com.billing.repository.*;
import com.billing.service.BillingService;

@Service
@Transactional
public class BillingServiceImpl implements BillingService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;

    public BillingServiceImpl(CustomerRepository customerRepository,
                              ProductRepository productRepository,
                              CompanyRepository companyRepository,
                              InvoiceRepository invoiceRepository,
                              PaymentRepository paymentRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
    }

    // ================= COMPANY =================
    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
    }

    // ================= CUSTOMER =================
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        existingCustomer.setName(customer.getName());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setGst(customer.getGst());
        existingCustomer.setAddress(customer.getAddress());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }

    // ================= PRODUCT =================
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ================= INVOICE SAVE =================
    @Override
    public Invoice saveInvoice(Invoice invoice) {

        double subTotal = 0.0;
        double totalAmount = 0.0;

        for (InvoiceItem item : invoice.getItems()) {

            item.setInvoice(invoice);

            double price = item.getPrice() != null ? item.getPrice() : 0;
            double qty = item.getQuantity() != null ? item.getQuantity() : 0;
            double taxPercent = item.getTax() != null ? item.getTax() : 0;
            double discount = item.getDiscount() != null ? item.getDiscount() : 0;

            double rowBase = price * qty;
            double taxAmount = (rowBase * taxPercent) / 100;
            double rowTotal = rowBase + taxAmount - discount;

            item.setRowTotal(rowTotal);

            subTotal += rowBase;
            totalAmount += rowTotal;
        }

        invoice.setSubTotal(subTotal);
        invoice.setTotalAmount(totalAmount);
        invoice.setBalanceAmount(
                invoice.getAdvanceAmount() != null
                        ? totalAmount - invoice.getAdvanceAmount()
                        : totalAmount
        );

        invoice.setInvoiceDate(LocalDate.now());

        return invoiceRepository.save(invoice);
    }

    // ================= INVOICE UPDATE =================
    @Override
    public Invoice updateInvoice(Long id, Invoice invoice) {

        Invoice existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));

        existingInvoice.setInvoiceNumber(invoice.getInvoiceNumber());
        existingInvoice.setCustomer(invoice.getCustomer());
        existingInvoice.setCompany(invoice.getCompany());
        existingInvoice.setEmployee(invoice.getEmployee());
        existingInvoice.setAdvanceAmount(invoice.getAdvanceAmount());
        existingInvoice.setPaymentStatus(invoice.getPaymentStatus());

        existingInvoice.getItems().clear();

        double subTotal = 0.0;
        double totalAmount = 0.0;

        for (InvoiceItem item : invoice.getItems()) {

            item.setInvoice(existingInvoice);

            double price = item.getPrice() != null ? item.getPrice() : 0;
            double qty = item.getQuantity() != null ? item.getQuantity() : 0;
            double taxPercent = item.getTax() != null ? item.getTax() : 0;
            double discountPercent = item.getDiscount() != null ? item.getDiscount() : 0;

            double rowBase = price * qty;

            // % calculations
            double taxAmount = (rowBase * taxPercent) / 100;
            double discountAmount = (rowBase * discountPercent) / 100;

            double rowTotal = rowBase + taxAmount - discountAmount;

            item.setRowTotal(rowTotal);

            subTotal += rowBase;
            totalAmount += rowTotal;

            existingInvoice.getItems().add(item);
        }

        existingInvoice.setSubTotal(subTotal);
        existingInvoice.setTotalAmount(totalAmount);
        existingInvoice.setBalanceAmount(
                invoice.getAdvanceAmount() != null
                        ? totalAmount - invoice.getAdvanceAmount()
                        : totalAmount
        );

        return invoiceRepository.save(existingInvoice);
    }

    // ================= INVOICE FETCH =================
    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
    }

    // ================= PAYMENT =================
    @Override
    public Payment savePayment(Payment payment) {
        payment.setPaymentDate(LocalDate.now());
        return paymentRepository.save(payment);
    }
}

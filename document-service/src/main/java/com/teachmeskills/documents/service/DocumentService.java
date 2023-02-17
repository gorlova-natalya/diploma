package com.teachmeskills.documents.service;

import com.teachmeskills.documents.model.Asset;
import com.teachmeskills.documents.model.CashReceipt;
import com.teachmeskills.documents.model.CashVoucher;
import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Invoice;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.repository.CashReceiptRepository;
import com.teachmeskills.documents.repository.CashVoucherRepository;
import com.teachmeskills.documents.repository.DocumentRepository;
import com.teachmeskills.documents.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentService {

    private final CashReceiptRepository cashReceiptRepository;
    private final CashVoucherRepository cashVoucherRepository;
    private final DocumentRepository documentRepository;
    private final InvoiceRepository invoiceRepository;

    public CashReceipt createCashReceipt(DocumentType documentType, int documentNumber, String purpose,
                                         LocalDate documentDate, Employee employee, Organization organization,
                                         double sum, String annex) {
        final CashReceipt cashReceipt = CashReceipt.builder().documentType(documentType).documentNumber(documentNumber)
                .purpose(purpose).documentDate(documentDate).employee(employee).organization(organization)
                .sum(sum).annex(annex).build();

        cashReceiptRepository.save(cashReceipt);
        return cashReceipt;
    }

    public CashVoucher createCashVoucher(DocumentType documentType, int documentNumber, String purpose,
                                         LocalDate documentDate, Employee employee, Organization organization,
                                         double sum, String annex, String passport) {
        final CashVoucher cashVoucher = CashVoucher.builder().documentType(documentType).documentNumber(documentNumber)
                .purpose(purpose).documentDate(documentDate).employee(employee).organization(organization)
                .sum(sum).annex(annex).passport(passport).build();

        cashVoucherRepository.save(cashVoucher);
        return cashVoucher;
    }

    public Invoice createInvoice(int documentNumber, LocalDate documentDate, Organization organization,
                                 Department fromDepartment, Department toDepartment, Employee fromEmployee, Employee toEmployee,
                                 DocumentType documentType, Asset asset) {
        final Invoice invoice = Invoice.builder().documentNumber(documentNumber).documentDate(documentDate)
                .organization(organization).fromDepartment(fromDepartment).toDepartment(toDepartment)
                .fromEmployee(fromEmployee).toEmployee(toEmployee).documentType(documentType).asset(asset)
                .build();

        invoiceRepository.save(invoice);
        return invoice;
    }

    public Optional<DocumentType> getDocumentType(Long id) {
        return documentRepository.getDocumentTypeById(id);
    }

    public List<DocumentType> getDocumentTypes() {
        return documentRepository.findAll();
    }
}

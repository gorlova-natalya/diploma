package com.teachmeskills.documents.service;

import com.teachmeskills.documents.model.AssetCount;
import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Invoice;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public Invoice createInvoice(int documentNumber, LocalDate documentDate, Organization organization,
                                 Department fromDepartment, Department toDepartment, Employee fromEmployee,
                                 Employee toEmployee, DocumentType documentType, List<AssetCount> assets) {
        final Invoice invoice = Invoice.builder().documentNumber(documentNumber).documentDate(documentDate)
                .organization(organization).fromDepartment(fromDepartment).toDepartment(toDepartment)
                .fromEmployee(fromEmployee).toEmployee(toEmployee).documentType(documentType).assetCount(assets)
                .build();

        invoiceRepository.save(invoice);
        return invoice;
    }
}

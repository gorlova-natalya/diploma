package com.teachmeskills.documents;

import com.teachmeskills.documents.model.Asset;
import com.teachmeskills.documents.model.AssetCount;
import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Invoice;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.repository.InvoiceRepository;
import com.teachmeskills.documents.service.InvoiceService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Disabled
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = InvoiceService.class)
public class InvoiceServiceTest {

    @Autowired
    private InvoiceService invoiceService;

    @MockBean
    private InvoiceRepository invoiceRepository;

    @Test
    public void createInvoiceTest() {
        List<AssetCount> assetCounts = new ArrayList<>();
        assetCounts.add(AssetCount.builder().id(1L).count(1).asset(new Asset()).sum(1.0).build());
        assetCounts.add(AssetCount.builder().id(2L).count(2).asset(new Asset()).sum(2.0).build());
        Invoice invoice = Invoice.builder().documentNumber(12345)
                .documentDate(LocalDate.of(2023, 3, 1))
                .organization(new Organization(1L, "OAO", 1234567, new Employee()))
                .fromDepartment(new Department())
                .toDepartment(new Department())
                .fromEmployee(new Employee())
                .toEmployee(new Employee())
                .documentType(new DocumentType())
                .assetCount(assetCounts)
                .build();
        invoice.getDocumentType().setId(1L);
        invoice.getFromDepartment().setId(1L);
        invoice.getToDepartment().setId(2L);
        invoice.getFromEmployee().setId(1L);
        invoice.getToEmployee().setId(2L);

        when(invoiceRepository.save(invoice)).thenReturn(invoice);

        Invoice created = invoiceService.createInvoice(invoice.getDocumentNumber(), invoice.getDocumentDate(),
                invoice.getOrganization(), invoice.getFromDepartment(), invoice.getToDepartment(),
                invoice.getFromEmployee(), invoice.getToEmployee(), invoice.getDocumentType(), invoice.getAssetCount());

        assertEquals(invoice, created);

        verify(invoiceRepository).save(invoice);
    }
}

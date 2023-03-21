package com.teachmeskills;

import com.teachmeskills.security.client.CashReceiptClient;
import com.teachmeskills.security.client.CashVoucherClient;
import com.teachmeskills.security.client.InvoiceClient;
import com.teachmeskills.security.service.DocumentService;
import org.example.common.dto.document.CashReceiptDto;
import org.example.common.dto.document.CashVoucherDto;
import org.example.common.dto.document.CreateCashReceiptDto;
import org.example.common.dto.document.CreateCashVoucherDto;
import org.example.common.dto.document.CreateInvoiceDto;
import org.example.common.dto.document.DepartmentDto;
import org.example.common.dto.document.DocumentTypeDto;
import org.example.common.dto.document.EmployeeDto;
import org.example.common.dto.document.InvoiceDto;
import org.example.common.dto.document.OrganizationDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@Disabled
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = DocumentService.class)
public class DocumentServiceTest {

    @Autowired
    private DocumentService documentService;

    @MockBean
    private CashReceiptClient cashReceiptClient;

    @MockBean
    private CashVoucherClient cashVoucherClient;

    @MockBean
    private InvoiceClient invoiceClient;

    @Test
    public void createCashReceiptTest() {
        final CreateCashReceiptDto createCashReceiptDto = CreateCashReceiptDto.builder()
                .purpose("командировочные расходы").documentTypeId(1L).employeeId(1L).organizationId(1L).sum(10)
                .documentNumber(12345).documentDate("01.03.2023").annex("паспорт")
                .build();
        final CashReceiptDto cashReceiptDto = CashReceiptDto.builder().purpose("командировочные расходы")
                .documentType(DocumentTypeDto.builder().id(1L).build()).employee(EmployeeDto.builder().id(1L).build())
                .organization(new OrganizationDto(1L, "OAO", 1234567,
                        EmployeeDto.builder().build()))
                .sum(10)
                .documentNumber(12345).documentDate("01.03.2023").annex("паспорт")
                .build();
        given(cashReceiptClient.createCashReceipt(createCashReceiptDto)).willReturn(cashReceiptDto);
        CashReceiptDto expected = documentService.createOrder(createCashReceiptDto);

        assertEquals(expected, cashReceiptDto);

        verify(cashReceiptClient).createCashReceipt(createCashReceiptDto);
    }

    @Test
    public void createCashVoucherTest() {
        final CreateCashVoucherDto createCashVoucherDto = CreateCashVoucherDto.builder()
                .purpose("командировочные расходы").documentTypeId(1L).employeeId(1L).organizationId(1L).sum(10)
                .documentNumber(12345).documentDate("01.03.2023").annex("паспорт")
                .passport("паспортные данные").build();
        final CashVoucherDto cashVoucherDto = CashVoucherDto.builder().purpose("командировочные расходы")
                .documentType(DocumentTypeDto.builder().id(1L).build()).employee(EmployeeDto.builder().id(1L).build())
                .organization(new OrganizationDto(1L, "OAO", 1234567,
                        EmployeeDto.builder().build()))
                .sum(10)
                .documentNumber(12345).documentDate("01.03.2023").annex("паспорт")
                .passport("паспортные данные").build();
        given(cashVoucherClient.createCashVoucher(createCashVoucherDto)).willReturn(cashVoucherDto);
        CashVoucherDto expected = documentService.createVoucher(createCashVoucherDto);

        assertEquals(expected, cashVoucherDto);

        verify(cashVoucherClient).createCashVoucher(createCashVoucherDto);
    }

    @Test
    public void createInvoiceTest() {

        final CreateInvoiceDto createInvoiceDto = new CreateInvoiceDto(1L, 12345, "01.03.2023",
                1L, 1L, 2L, 1L, 2L, 1L, new ArrayList<>());
        final InvoiceDto invoiceDto = InvoiceDto.builder().id(1L).documentNumber(12345)
                .documentDate("01.03.2023")
                .organization(OrganizationDto.builder().id(1L).build()).fromDepartment(DepartmentDto.builder().id(1L).build())
                .toDepartment(DepartmentDto.builder().id(2L).build()).fromEmployee(EmployeeDto.builder().id(1L).build())
                .toEmployee(EmployeeDto.builder().id(1L).build())
                .documentType(DocumentTypeDto.builder().id(1L).build()).assetCount(new ArrayList<>()).build();
        given(invoiceClient.createInvoice(createInvoiceDto)).willReturn(invoiceDto);
        InvoiceDto expected = documentService.createInvoice(createInvoiceDto);

        assertEquals(expected, invoiceDto);

        verify(invoiceClient).createInvoice(createInvoiceDto);
    }
}

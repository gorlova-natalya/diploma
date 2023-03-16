package com.teachmeskills.documents;

import com.teachmeskills.documents.controller.CashReceiptController;
import com.teachmeskills.documents.controller.InvoiceController;
import com.teachmeskills.documents.converter.InvoiceConverter;
import com.teachmeskills.documents.facade.InvoiceFacade;
import com.teachmeskills.documents.model.Asset;
import com.teachmeskills.documents.model.AssetCount;
import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Invoice;
import com.teachmeskills.documents.model.Organization;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(CashReceiptController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private InvoiceFacade invoiceFacade;

    @MockBean
    private InvoiceConverter invoiceConverter;

    @Test
    public void createInvoiceTest() throws Exception {

        final CreateInvoiceDto createInvoiceDto = CreateInvoiceDto.builder()
                .documentNumber(12345)
                .documentDate("2023-03-01")
                .documentTypeId(1L)
                .organization(1L)
                .fromDepartment(1L)
                .toDepartment(2L)
                .fromEmployee(1L)
                .toEmployee(2L)
                .documentTypeId(1L)
                .assetCount(new ArrayList<>())
                .build();

        List<AssetCount> assetCounts = new ArrayList<>();
        assetCounts.add(AssetCount.builder().id(1L).count(1).asset(new Asset()).sum(1.0).build());
        assetCounts.add(AssetCount.builder().id(2L).count(2).asset(new Asset()).sum(2.0).build());
        final Invoice invoice = Invoice.builder().documentNumber(12345)
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

        final InvoiceDto expected = InvoiceDto.builder()
                .documentNumber(12345)
                .documentDate("01.03.2023")
                .organization(new OrganizationDto(1L, "OAO", 1234567, EmployeeDto.builder().build()))
                .fromDepartment(DepartmentDto.builder().id(1L).build())
                .toDepartment(DepartmentDto.builder().id(2L).build())
                .fromEmployee(EmployeeDto.builder().id(1L).build())
                .toEmployee(EmployeeDto.builder().id(2L).build())
                .documentType(DocumentTypeDto.builder().id(1L).build())
                .assetCount(new ArrayList<>())
                .build();

        when(invoiceFacade.createInvoice(createInvoiceDto)).thenReturn(invoice);
        when(invoiceConverter.toDto(invoice)).thenReturn(expected);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/invoices")
                        .content(mapper.writeValueAsString(createInvoiceDto))
                        .contentType(APPLICATION_JSON_VALUE)
                        .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpect(status().isAccepted())
                .andReturn();

        then(invoiceFacade)
                .should()
                .createInvoice(createInvoiceDto);

        InvoiceDto returned = mapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<InvoiceDto>() {
                });
        assertEquals(expected, returned);
    }
}

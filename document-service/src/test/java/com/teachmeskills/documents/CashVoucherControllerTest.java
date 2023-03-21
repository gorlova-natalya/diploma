package com.teachmeskills.documents;

import com.teachmeskills.documents.controller.CashReceiptController;
import com.teachmeskills.documents.controller.CashVoucherController;
import com.teachmeskills.documents.converter.CashVoucherConverter;
import com.teachmeskills.documents.facade.CashVoucherFacade;
import com.teachmeskills.documents.model.CashVoucher;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import org.example.common.dto.document.CashVoucherDto;
import org.example.common.dto.document.CreateCashVoucherDto;
import org.example.common.dto.document.DocumentTypeDto;
import org.example.common.dto.document.EmployeeDto;
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
@ContextConfiguration(classes = CashVoucherController.class)
public class CashVoucherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private CashVoucherFacade cashVoucherFacade;

    @MockBean
    private CashVoucherConverter cashVoucherConverter;

    @Test
    public void createCashVoucherTest() throws Exception {

        final CreateCashVoucherDto createCashVoucherDto = CreateCashVoucherDto.builder()
                .purpose("командировочные расходы")
                .documentTypeId(1L)
                .employeeId(1L)
                .organizationId(1L)
                .sum(10.0)
                .documentNumber(12345)
                .documentDate("01.03.2023")
                .annex("паспорт")
                .passport("паспортные данные")
                .build();

        final CashVoucher cashVoucher = CashVoucher.builder()
                .purpose("командировочные расходы")
                .documentType(new DocumentType())
                .employee(new Employee())
                .organization(new Organization(1L, "OAO", 12345, new Employee()))
                .sum(10.0)
                .documentNumber(12345)
                .documentDate(LocalDate.of(2023, 3, 1))
                .annex("паспорт")
                .passport("паспортные данные")
                .build();
        cashVoucher.getDocumentType().setId(1L);
        cashVoucher.getDocumentType().setTypeName("кассовый ордер");
        cashVoucher.getDocumentType().setSignersList(new ArrayList<>());
        cashVoucher.getEmployee().setId(1L);

        final CashVoucherDto expected = CashVoucherDto.builder()
                .purpose("командировочные расходы")
                .documentType(DocumentTypeDto.builder().id(1L).typeName("кассовый ордер")
                        .signersList(new ArrayList<>()).build())
                .employee(EmployeeDto.builder().id(1L).build())
                .organization(new OrganizationDto(1L, "OAO", 12345, EmployeeDto.builder().build()))
                .sum(10.0)
                .documentNumber(12345)
                .documentDate("01.03.2023")
                .annex("паспорт")
                .passport("паспортные данные")
                .build();

        when(cashVoucherFacade.createCashVoucher(createCashVoucherDto)).thenReturn(cashVoucher);
        when(cashVoucherConverter.toDto(cashVoucher)).thenReturn(expected);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/cash-vouchers")
                        .content(mapper.writeValueAsString(createCashVoucherDto))
                        .contentType(APPLICATION_JSON_VALUE)
                        .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpect(status().isAccepted())
                .andReturn();

        then(cashVoucherFacade)
                .should()
                .createCashVoucher(createCashVoucherDto);

        CashVoucherDto returned = mapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<CashVoucherDto>() {
                });
        assertEquals(expected, returned);
    }
}

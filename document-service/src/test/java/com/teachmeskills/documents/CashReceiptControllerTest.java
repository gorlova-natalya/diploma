package com.teachmeskills.documents;

import com.teachmeskills.documents.controller.CashReceiptController;
import com.teachmeskills.documents.converter.CashReceiptConverter;
import com.teachmeskills.documents.facade.CashReceiptFacade;
import com.teachmeskills.documents.model.CashReceipt;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import org.example.common.dto.document.CashReceiptDto;
import org.example.common.dto.document.CreateCashReceiptDto;
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
@ContextConfiguration(classes = CashReceiptController.class)
public class CashReceiptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private CashReceiptFacade cashReceiptFacade;

    @MockBean
    private CashReceiptConverter cashReceiptConverter;

    @Test
    public void createCashReceiptTest() throws Exception {

        final CreateCashReceiptDto createCashReceiptDto = CreateCashReceiptDto.builder()
                .purpose("командировочные расходы")
                .documentTypeId(1L)
                .employeeId(1L)
                .organizationId(1L)
                .sum(10.0)
                .documentNumber(12345)
                .documentDate("01.03.2023")
                .annex("паспорт")
                .build();

        final CashReceipt cashReceipt = CashReceipt.builder()
                .purpose("командировочные расходы")
                .documentType(new DocumentType())
                .employee(new Employee())
                .organization(new Organization(1L, "OAO", 12345, new Employee()))
                .sum(10.0)
                .documentNumber(12345)
                .documentDate(LocalDate.of(2023, 3, 1))
                .annex("паспорт")
                .build();
        cashReceipt.getDocumentType().setId(1L);
        cashReceipt.getDocumentType().setTypeName("кассовый ордер");
        cashReceipt.getDocumentType().setSignersList(new ArrayList<>());
        cashReceipt.getEmployee().setId(1L);

        final CashReceiptDto expected = CashReceiptDto.builder()
                .purpose("командировочные расходы")
                .documentType(DocumentTypeDto.builder().id(1L).typeName("кассовый ордер")
                        .signersList(new ArrayList<>()).build())
                .employee(EmployeeDto.builder().id(1L).build())
                .organization(new OrganizationDto(1L, "OAO", 12345, EmployeeDto.builder().build()))
                .sum(10.0)
                .documentNumber(12345)
                .documentDate(LocalDate.of(2023, 3, 1))
                .annex("паспорт")
                .build();

        when(cashReceiptFacade.createCashReceipt(createCashReceiptDto)).thenReturn(cashReceipt);
        when(cashReceiptConverter.toDto(cashReceipt)).thenReturn(expected);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/cash-receipts")
                        .content(mapper.writeValueAsString(createCashReceiptDto))
                        .contentType(APPLICATION_JSON_VALUE)
                        .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpect(status().isAccepted())
                .andReturn();

        then(cashReceiptFacade)
                .should()
                .createCashReceipt(createCashReceiptDto);

        CashReceiptDto returned = mapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<CashReceiptDto>() {
                });
        assertEquals(expected, returned);
    }
}

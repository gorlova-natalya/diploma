package com.teachmeskills.documents;

import com.teachmeskills.documents.facade.CashReceiptFacade;
import com.teachmeskills.documents.model.CashReceipt;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.service.CashReceiptService;
import com.teachmeskills.documents.service.DocumentTypeService;
import com.teachmeskills.documents.service.EmployeeService;
import com.teachmeskills.documents.service.OrganizationService;
import org.example.common.dto.document.CreateCashReceiptDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Disabled
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = CashReceiptFacade.class)
public class CashReceiptFacadeTest {

    @Autowired
    private CashReceiptFacade cashReceiptFacade;

    @MockBean
    private DocumentTypeService documentTypeService;

    @MockBean
    private CashReceiptService cashReceiptService;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private OrganizationService organizationService;

    @Test
    public void createCashReceiptTest() {
        final CreateCashReceiptDto createCashReceiptDto = CreateCashReceiptDto.builder()
                .purpose("командировочные расходы")
                .documentTypeId(1L)
                .employeeId(1L)
                .organizationId(1L)
                .sum(10.0)
                .documentNumber(12345)
                .documentDate("2023-03-01")
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

        when(organizationService.get(createCashReceiptDto.getOrganizationId()))
                .thenReturn(Optional.ofNullable(cashReceipt.getOrganization()));
        when(employeeService.get(createCashReceiptDto.getEmployeeId()))
                .thenReturn(Optional.ofNullable(cashReceipt.getEmployee()));
        when(documentTypeService.getDocumentType(createCashReceiptDto.getDocumentTypeId()))
                .thenReturn(Optional.ofNullable(cashReceipt.getDocumentType()));
        when(cashReceiptService.createCashReceipt(cashReceipt.getDocumentType(), createCashReceiptDto.getDocumentNumber(),
                createCashReceiptDto.getPurpose(), LocalDate.parse(createCashReceiptDto.getDocumentDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")), cashReceipt.getEmployee(),
                cashReceipt.getOrganization(), createCashReceiptDto.getSum(), createCashReceiptDto.getAnnex()))
                .thenReturn(cashReceipt);

        CashReceipt created = cashReceiptFacade.createCashReceipt(createCashReceiptDto);

        assertEquals(cashReceipt, created);
        verify(cashReceiptService, times(1)).createCashReceipt(cashReceipt.getDocumentType(), createCashReceiptDto.getDocumentNumber(),
                createCashReceiptDto.getPurpose(), LocalDate.parse(createCashReceiptDto.getDocumentDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")), cashReceipt.getEmployee(),
                cashReceipt.getOrganization(), createCashReceiptDto.getSum(), createCashReceiptDto.getAnnex());
    }
}

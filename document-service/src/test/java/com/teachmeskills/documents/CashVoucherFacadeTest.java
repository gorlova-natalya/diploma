package com.teachmeskills.documents;

import com.teachmeskills.documents.facade.CashVoucherFacade;
import com.teachmeskills.documents.model.CashVoucher;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.service.CashVoucherService;
import com.teachmeskills.documents.service.DocumentTypeService;
import com.teachmeskills.documents.service.EmployeeService;
import com.teachmeskills.documents.service.OrganizationService;
import org.example.common.dto.document.CreateCashVoucherDto;
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
@ContextConfiguration(classes = CashVoucherFacade.class)
public class CashVoucherFacadeTest {

    @Autowired
    private CashVoucherFacade cashVoucherFacade;

    @MockBean
    private DocumentTypeService documentTypeService;

    @MockBean
    private CashVoucherService cashVoucherService;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private OrganizationService organizationService;

    @Test
    public void createCashVoucherTest() {
        final CreateCashVoucherDto createCashVoucherDto = CreateCashVoucherDto.builder()
                .purpose("командировочные расходы")
                .documentTypeId(1L)
                .employeeId(1L)
                .organizationId(1L)
                .sum(10.0)
                .documentNumber(12345)
                .documentDate("2023-03-01")
                .annex("паспорт")
                .passport("аспортные данные")
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
                .passport("аспортные данные")
                .build();
        cashVoucher.getDocumentType().setId(1L);
        cashVoucher.getDocumentType().setTypeName("кассовый ордер");
        cashVoucher.getDocumentType().setSignersList(new ArrayList<>());
        cashVoucher.getEmployee().setId(1L);

        when(organizationService.get(createCashVoucherDto.getOrganizationId()))
                .thenReturn(Optional.ofNullable(cashVoucher.getOrganization()));
        when(employeeService.get(createCashVoucherDto.getEmployeeId()))
                .thenReturn(Optional.ofNullable(cashVoucher.getEmployee()));
        when(documentTypeService.getDocumentType(createCashVoucherDto.getDocumentTypeId()))
                .thenReturn(Optional.ofNullable(cashVoucher.getDocumentType()));
        when(cashVoucherService.createCashVoucher(cashVoucher.getDocumentType(), createCashVoucherDto.getDocumentNumber(),
                createCashVoucherDto.getPurpose(), LocalDate.parse(createCashVoucherDto.getDocumentDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")), cashVoucher.getEmployee(),
                cashVoucher.getOrganization(), createCashVoucherDto.getSum(), createCashVoucherDto.getAnnex(),
                createCashVoucherDto.getPassport()))
                .thenReturn(cashVoucher);

        CashVoucher created = cashVoucherFacade.createCashVoucher(createCashVoucherDto);

        assertEquals(cashVoucher, created);
        verify(cashVoucherService, times(1)).createCashVoucher(cashVoucher.getDocumentType(), createCashVoucherDto.getDocumentNumber(),
                createCashVoucherDto.getPurpose(), LocalDate.parse(createCashVoucherDto.getDocumentDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")), cashVoucher.getEmployee(),
                cashVoucher.getOrganization(), createCashVoucherDto.getSum(), createCashVoucherDto.getAnnex(),
                createCashVoucherDto.getPassport());
    }
}

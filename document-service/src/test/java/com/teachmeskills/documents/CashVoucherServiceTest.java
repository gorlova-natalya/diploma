package com.teachmeskills.documents;

import com.teachmeskills.documents.model.CashVoucher;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.repository.CashVoucherRepository;
import com.teachmeskills.documents.service.CashVoucherService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Disabled
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = CashVoucherService.class)
public class CashVoucherServiceTest {

    @Autowired
    private CashVoucherService cashVoucherService;

    @MockBean
    private CashVoucherRepository cashVoucherRepository;

    @Test
    public void createCashVoucherTest() {
        CashVoucher cashVoucher = CashVoucher.builder().purpose("командировочные расходы")
                .documentType(new DocumentType()).employee(new Employee())
                .organization(new Organization(1L, "OAO", 1234567, new Employee()))
                .sum(10).documentNumber(12345).documentDate(LocalDate.of(2023, 3, 1))
                .annex("паспорт").passport("паспортные данные")
                .build();
        cashVoucher.getDocumentType().setId(1L);
        cashVoucher.getEmployee().setId(1L);

        when(cashVoucherRepository.save(cashVoucher)).thenReturn(cashVoucher);

        CashVoucher created = cashVoucherService.createCashVoucher(cashVoucher.getDocumentType(),
                cashVoucher.getDocumentNumber(), cashVoucher.getPurpose(), cashVoucher.getDocumentDate(),
                cashVoucher.getEmployee(), cashVoucher.getOrganization(), cashVoucher.getSum(),
                cashVoucher.getAnnex(), cashVoucher.getPassport());

        assertEquals(cashVoucher, created);

        verify(cashVoucherRepository).save(cashVoucher);
    }
}

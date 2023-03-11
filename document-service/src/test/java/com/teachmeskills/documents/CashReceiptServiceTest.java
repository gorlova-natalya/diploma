package com.teachmeskills.documents;

import com.teachmeskills.documents.model.CashReceipt;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.repository.CashReceiptRepository;
import com.teachmeskills.documents.service.CashReceiptService;
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
@ContextConfiguration(classes = CashReceiptService.class)
public class CashReceiptServiceTest {

    @Autowired
    private CashReceiptService cashReceiptService;

    @MockBean
    private CashReceiptRepository cashReceiptRepository;

    @Test
    public void createCashReceiptTest() {
        CashReceipt cashReceipt = CashReceipt.builder().purpose("командировочные расходы")
                .documentType(new DocumentType()).employee(new Employee())
                .organization(new Organization(1L, "OAO", 1234567, new Employee()))
                .sum(10).documentNumber(12345).documentDate(LocalDate.of(2023, 3, 1))
                .annex("паспорт")
                .build();
        cashReceipt.getDocumentType().setId(1L);
        cashReceipt.getEmployee().setId(1L);

        when(cashReceiptRepository.save(cashReceipt)).thenReturn(cashReceipt);

        CashReceipt created = cashReceiptService.createCashReceipt(cashReceipt.getDocumentType(),
                cashReceipt.getDocumentNumber(), cashReceipt.getPurpose(), cashReceipt.getDocumentDate(),
                cashReceipt.getEmployee(), cashReceipt.getOrganization(), cashReceipt.getSum(),
                cashReceipt.getAnnex());

        assertEquals(cashReceipt, created);

        verify(cashReceiptRepository).save(cashReceipt);
    }
}

package com.teachmeskills.documents.service;

import com.teachmeskills.documents.model.CashVoucher;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.repository.CashVoucherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CashVoucherService {

    private final CashVoucherRepository cashVoucherRepository;

    public CashVoucher createCashVoucher(DocumentType documentType, int documentNumber, String purpose,
                                         LocalDate documentDate, Employee employee, Organization organization,
                                         double sum, String annex, String passport) {
        final CashVoucher cashVoucher = CashVoucher.builder().documentType(documentType).documentNumber(documentNumber)
                .purpose(purpose).documentDate(documentDate).employee(employee).organization(organization)
                .sum(sum).annex(annex).passport(passport).build();

        cashVoucherRepository.save(cashVoucher);
        return cashVoucher;
    }
}

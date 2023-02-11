package com.teachmeskills.documents.service;

import com.teachmeskills.documents.model.CashReceipt;
import com.teachmeskills.documents.model.CashVoucher;
import com.teachmeskills.documents.model.DocumentType;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.model.Organization;
import com.teachmeskills.documents.repository.CashReceiptRepository;
import com.teachmeskills.documents.repository.CashVoucherRepository;
import com.teachmeskills.documents.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentService {

    private final CashReceiptRepository cashReceiptRepository;
    private final CashVoucherRepository cashVoucherRepository;
    private final DocumentRepository documentRepository;

    public CashReceipt createCashReceipt(DocumentType documentType, int documentNumber, String purpose,
                                         LocalDate documentDate, Employee employee, Organization organization,
                                         double sum, String annex) {
        final CashReceipt cashReceipt = CashReceipt.builder().documentType(documentType).documentNumber(documentNumber)
                .purpose(purpose).documentDate(documentDate).employee(employee).organization(organization)
                .sum(sum).annex(annex).build();

        cashReceiptRepository.save(cashReceipt);
        return cashReceipt;
    }

    public CashVoucher createCashVoucher(DocumentType documentType, int documentNumber, String purpose,
                                         LocalDate documentDate, Employee employee, Organization organization,
                                         double sum, String annex, String passport) {
        final CashVoucher cashVoucher = CashVoucher.builder().documentType(documentType).documentNumber(documentNumber)
                .purpose(purpose).documentDate(documentDate).employee(employee).organization(organization)
                .sum(sum).annex(annex).passport(passport).build();

        cashVoucherRepository.save(cashVoucher);
        return cashVoucher;
    }

    public Optional<DocumentType> getDocumentType(Long id) {
        return documentRepository.getDocumentTypeById(id);
    }

    public List<DocumentType> getDocumentTypes() {
        return documentRepository.findAll();
    }
}

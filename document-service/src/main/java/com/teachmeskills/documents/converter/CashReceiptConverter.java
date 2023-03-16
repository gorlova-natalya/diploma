package com.teachmeskills.documents.converter;

import org.example.common.dto.document.CashReceiptDto;
import com.teachmeskills.documents.model.CashReceipt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {EmployeeConverter.class, DocumentTypeConverter.class, OrganizationConverter.class})
public interface CashReceiptConverter {

    @Mapping(target = "documentDate", source = "cashReceipt.documentDate", dateFormat = "dd.MM.yyyy")
    CashReceiptDto toDto(CashReceipt cashReceipt);
}

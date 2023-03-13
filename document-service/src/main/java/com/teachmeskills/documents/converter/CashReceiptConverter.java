package com.teachmeskills.documents.converter;

import org.example.common.dto.document.CashReceiptDto;
import com.teachmeskills.documents.model.CashReceipt;
import org.mapstruct.Mapper;

@Mapper(uses = {EmployeeConverter.class, DocumentTypeConverter.class, OrganizationConverter.class})
public interface CashReceiptConverter {

    CashReceiptDto toDto(CashReceipt cashReceipt);
}

package com.teachmeskills.documents.converter;

import com.teachmeskills.documents.model.CashVoucher;
import org.example.common.dto.document.CashVoucherDto;
import org.mapstruct.Mapper;

@Mapper(uses = {EmployeeConverter.class, DocumentTypeConverter.class, OrganizationConverter.class})
public interface CashVoucherConverter {

    CashVoucherDto toDto(CashVoucher cashVoucher);
}

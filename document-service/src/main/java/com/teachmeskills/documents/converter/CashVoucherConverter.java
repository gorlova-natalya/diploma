package com.teachmeskills.documents.converter;

import com.teachmeskills.documents.model.CashVoucher;
import org.example.common.dto.document.CashVoucherDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {EmployeeConverter.class, DocumentTypeConverter.class, OrganizationConverter.class})
public interface CashVoucherConverter {

    @Mapping(target = "documentDate", source = "cashVoucher.documentDate", dateFormat = "dd.MM.yyyy")
    CashVoucherDto toDto(CashVoucher cashVoucher);
}

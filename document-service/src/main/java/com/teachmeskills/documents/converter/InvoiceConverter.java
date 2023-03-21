package com.teachmeskills.documents.converter;

import com.teachmeskills.documents.model.Invoice;
import org.example.common.dto.document.InvoiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {EmployeeConverter.class, DocumentTypeConverter.class, OrganizationConverter.class,
                DepartmentConverter.class, AssetCountConverter.class})
public interface InvoiceConverter {

    @Mapping(target = "documentDate", source = "invoice.documentDate", dateFormat = "dd.MM.yyyy")
    InvoiceDto toDto(Invoice invoice);
}

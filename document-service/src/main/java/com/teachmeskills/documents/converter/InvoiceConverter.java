package com.teachmeskills.documents.converter;

import com.teachmeskills.documents.model.Invoice;
import org.example.common.dto.document.InvoiceDto;
import org.mapstruct.Mapper;

@Mapper(uses = {EmployeeConverter.class, DocumentTypeConverter.class, OrganizationConverter.class,
                DepartmentConverter.class, AssetCountConverter.class})
public interface InvoiceConverter {

    InvoiceDto toDto(Invoice invoice);
}

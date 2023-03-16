package org.example.common.dto.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateInvoiceDto {

    Long id;
    int documentNumber;
    String documentDate;
    Long organization;
    Long fromDepartment;
    Long toDepartment;
    Long fromEmployee;
    Long toEmployee;
    Long documentTypeId;
    List<ChooseAssetDto> assetCount = new ArrayList<>();
}

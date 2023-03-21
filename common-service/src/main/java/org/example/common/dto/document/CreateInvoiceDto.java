package org.example.common.dto.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateInvoiceDto {

    Long id;
    @NotNull(message = "Document number must not be empty")
    int documentNumber;
    @NotEmpty(message = "Document date must not be empty")
    String documentDate;
    Long organization;
    @NotNull(message = "Department must not be empty")
    Long fromDepartment;
    @NotNull(message = "Department must not be empty")
    Long toDepartment;
    @NotNull(message = "Employee must not be empty")
    Long fromEmployee;
    @NotNull(message = "Employee must not be empty")
    Long toEmployee;
    Long documentTypeId;
    List<ChooseAssetDto> assetCount = new ArrayList<>();
}

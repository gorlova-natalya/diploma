package org.example.common.dto.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceDto {

    Long id;
    int documentNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    LocalDate documentDate;
    Long organization;
    Long fromDepartment;
    Long toDepartment;
    Long fromEmployee;
    Long toEmployee;
    Long documentTypeId;
    List<ChooseAssetDto> assetCount = new ArrayList<>();
}

package org.example.common.dto.document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DocumentTypeDto {

    Long id;
    String typeName;
    List<EmployeeDto> signersList;

    public List<EmployeeDto> getSignersList() {
        return signersList;
    }
}

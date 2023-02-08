package com.teachmeskills.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeDto {

    Long id;
    String typeName;
    List<EmployeeDto> signersList;

    public List<EmployeeDto> getSignersList() {
        return signersList;
    }

    public void setSignersList(List<EmployeeDto> signersList) {
        this.signersList = signersList;
    }
}

package com.teachmeskills.security.service;

import com.teachmeskills.security.client.DocumentClient;
import com.teachmeskills.security.dto.CashReceiptDto;
import com.teachmeskills.security.dto.EmployeeDto;
import com.teachmeskills.security.dto.OrganizationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CashReceiptService {

    private final DocumentClient documentClient;

    public CashReceiptDto createOrder(final CashReceiptDto order) {
        final CashReceiptDto request = new CashReceiptDto();
        request.setDocumentNumber(order.getDocumentNumber());
        request.setDocumentType(order.getDocumentType());
        request.setDocumentDate(order.getDocumentDate());
        request.setEmployee(order.getEmployee());
        request.setOrganization(order.getOrganization());
        request.setPurpose(order.getPurpose());
        request.setSum(order.getSum());
        request.setAnnex(order.getAnnex());
        return documentClient.createCashReceipt(request);
    }

    public List<OrganizationDto> getOrganizations() {
        return documentClient.getAllOrganizations();
    }

    public List<EmployeeDto> getEmployees() {
        return documentClient.getEmployees();
    }
}

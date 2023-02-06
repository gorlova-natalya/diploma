package com.teachmeskills.documents.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@NonFinal
@Entity
@Table(name = "orders")
@Builder
public class CashReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "document_type_id", referencedColumnName = "id")
    DocumentType documentType;

    @Column(name = "order_number")
    int documentNumber;

    @Column(name = "purpose")
    String purpose;

    @Column(name = "order_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd.MM.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate documentDate;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    Organization organization;

    @Column(name = "order_sum")
    double sum;

    @Column(name = "annex")
    String annex;

    public CashReceipt(long id, DocumentType documentType, int documentNumber, String purpose, LocalDate documentDate, Employee employee, Organization organization, double sum, String annex) {
        this.id = id;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.purpose = purpose;
        this.documentDate = documentDate;
        this.employee = employee;
        this.organization = organization;
        this.sum = sum;
        this.annex = annex;
    }
}

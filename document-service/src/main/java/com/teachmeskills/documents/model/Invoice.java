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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@NonFinal
@Entity
@Table(name = "invoices")
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "invoice_number")
    int documentNumber;

    @Column(name = "invoice_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd.MM.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    LocalDate documentDate;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "from_department_id", referencedColumnName = "id")
    private Department fromDepartment;

    @ManyToOne
    @JoinColumn(name = "to_department_id", referencedColumnName = "id")
    private Department toDepartment;

    @ManyToOne
    @JoinColumn(name = "from_employee_id", referencedColumnName = "id")
    private Employee fromEmployee;

    @ManyToOne
    @JoinColumn(name = "to_employee_id", referencedColumnName = "id")
    private Employee toEmployee;

    @ManyToOne
    @JoinColumn(name = "document_type_id", referencedColumnName = "id")
    private DocumentType documentType;

    @NotEmpty
    @OneToMany(mappedBy = "asset")
    List<AssetCount> assetCount;

    public Invoice(Long id, int documentNumber, LocalDate documentDate, Organization organization,
                   Department fromDepartment, Department toDepartment, Employee fromEmployee, Employee toEmployee,
                   DocumentType documentType, List<AssetCount> assetCount) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.documentDate = documentDate;
        this.organization = organization;
        this.fromDepartment = fromDepartment;
        this.toDepartment = toDepartment;
        this.fromEmployee = fromEmployee;
        this.toEmployee = toEmployee;
        this.documentType = documentType;
        this.assetCount = assetCount;
    }
}

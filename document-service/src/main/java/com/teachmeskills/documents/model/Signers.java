package com.teachmeskills.documents.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@NonFinal
@Entity
@Table(name = "signers")
public class Signers {

    @Id
    long id;

    @ManyToOne
    @JoinColumn(name = "document_type_id")
    DocumentType documentType;

    @ManyToOne
    @JoinColumn(name = "employees_id")
    Employee employee;
}

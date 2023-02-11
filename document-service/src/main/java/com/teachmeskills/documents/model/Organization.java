package com.teachmeskills.documents.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@NonFinal
@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "organization_name")
    String name;

    @Column(name = "payer_ID_number")
    int payerNumber;

    @OneToOne
    @JoinColumn(name = "supervisor_id", referencedColumnName = "id")
    Employee supervisor;
}

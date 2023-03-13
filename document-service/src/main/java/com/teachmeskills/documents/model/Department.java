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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@NonFinal
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "department_name")
    String departmentName;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    Organization organization;

    public Department(long id, String departmentName, Organization organization) {
        this.id = id;
        this.departmentName = departmentName;
        this.organization = organization;
    }
}

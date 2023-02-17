package com.teachmeskills.documents.model;

import lombok.Builder;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@NonFinal
@Entity
@Table(name = "assets")
@Builder
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "asset_name")
    String assetName;

    @Column(name = "asset_count")
    Integer assetCount;

    @Column(name = "cost")
    Double cost;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    Department department;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "asset_unit_id", referencedColumnName = "id")
    AssetUnit assetUnits;

    @OneToMany(mappedBy = "invoice")
    List<AssetCount> invoiceAssetCount;

    public Asset(Long id, String assetName, Integer assetCount, Double cost, Department department, Employee employee,
                 AssetUnit assetUnits, List<AssetCount> invoiceAssetCount) {
        this.id = id;
        this.assetName = assetName;
        this.assetCount = assetCount;
        this.cost = cost;
        this.department = department;
        this.employee = employee;
        this.assetUnits = assetUnits;
        this.invoiceAssetCount = invoiceAssetCount;
    }
}

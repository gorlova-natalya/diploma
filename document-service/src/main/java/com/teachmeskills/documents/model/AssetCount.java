package com.teachmeskills.documents.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "asset_counts")
@NoArgsConstructor
@Builder
public class AssetCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    Asset asset;

    @Column(name = "asset_for_issue_count")
    int count;

    @Column(name = "asset_for_issue_sum")
    Double sum;

    public AssetCount(Long id, Invoice invoice, Asset asset, int count, Double sum) {
        this.id = id;
        this.invoice = invoice;
        this.asset = asset;
        this.count = count;
        this.sum = sum;
    }
}

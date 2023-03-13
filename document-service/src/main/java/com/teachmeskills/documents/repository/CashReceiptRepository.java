package com.teachmeskills.documents.repository;

import com.teachmeskills.documents.model.CashReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashReceiptRepository extends JpaRepository<CashReceipt, Long> {
}

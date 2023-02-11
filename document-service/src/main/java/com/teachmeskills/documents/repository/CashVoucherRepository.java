package com.teachmeskills.documents.repository;

import com.teachmeskills.documents.model.CashVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashVoucherRepository extends JpaRepository<CashVoucher, Long> {
}

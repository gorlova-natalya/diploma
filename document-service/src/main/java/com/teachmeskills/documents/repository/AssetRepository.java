package com.teachmeskills.documents.repository;

import com.teachmeskills.documents.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    @Override
    Optional<Asset> findById(Long id);
}

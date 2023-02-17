package com.teachmeskills.documents.service;

import com.teachmeskills.documents.model.Asset;
import com.teachmeskills.documents.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;

    public List<Asset> findAssets() {
        return assetRepository.findAll();
    }

    public Optional<Asset> getAsset(Long id) {
        return assetRepository.findById(id);
    }
}

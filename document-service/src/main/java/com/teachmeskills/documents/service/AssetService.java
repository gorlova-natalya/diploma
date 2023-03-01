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

    public List<Asset> getAssetsById(List<Long> ids) {
        return assetRepository.findAllById(ids);
    }

    public Optional<Asset> getAsset(Long id) {
        return assetRepository.findById(id);
    }

    public Double getSum(Long id, int count) {
        Asset asset = getAsset(id).orElse(null);
        if (asset != null) {
            return asset.getCost() * count;
        }
        return null;
    }
}

package com.teachmeskills.documents.service;

import com.teachmeskills.documents.model.Asset;
import com.teachmeskills.documents.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;

    public List<Asset> findAssets() {
        return assetRepository.findAll();
    }
}

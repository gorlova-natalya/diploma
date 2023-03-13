package com.teachmeskills.security.service;

import com.teachmeskills.security.client.AssetClient;
import org.example.common.dto.document.AssetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetClient assetClient;

    public List<AssetDto> getAssets() {
        return assetClient.getAssets();
    }
}

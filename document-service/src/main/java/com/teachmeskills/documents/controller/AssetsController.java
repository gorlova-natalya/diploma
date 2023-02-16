package com.teachmeskills.documents.controller;

import com.teachmeskills.documents.converter.AssetConverter;
import com.teachmeskills.documents.model.Asset;
import com.teachmeskills.documents.service.AssetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.document.AssetDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assets")
@RequiredArgsConstructor
@Slf4j
public class AssetsController {

    private final AssetService assetService;

    private final AssetConverter assetConverter;

    @GetMapping("/all")
    protected List<AssetDto> getAssets() {
        List<Asset> assets = assetService.findAssets();
        return assetConverter.toDto(assets);
    }
}

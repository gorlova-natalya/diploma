package com.teachmeskills.documents;

import com.teachmeskills.documents.model.Asset;
import com.teachmeskills.documents.repository.AssetRepository;
import com.teachmeskills.documents.service.AssetService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Disabled
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = AssetService.class)
public class AssetServiceTest {

    @Autowired
    private AssetService assetService;

    @MockBean
    private AssetRepository assetRepository;

    @Test
    public void getAllAssetsTest() {
        List<Asset> assets = new ArrayList<>();
        assets.add(mock(Asset.class));
        assets.add(Asset.builder().id(1L).assetName("Бумага").assetCount(12).assetNumber(21334).cost(7.80).build());

        given(assetService.findAssets()).willReturn(assets);
        List<Asset> expected = assetService.findAssets();

        assertEquals(expected, assets);

        verify(assetRepository).findAll();
    }

    @Test
    public void getAssetByIdTest() {
        Asset asset = Asset.builder().id(1L).assetName("Бумага").assetCount(12).assetNumber(21334).cost(7.80).build();
        final Optional<Asset> expectedAsset = Optional.of(asset);

        given(assetService.getAsset(asset.getId())).willReturn(expectedAsset);
        Optional<Asset> returnedAsset = assetService.getAsset(asset.getId());

        assertEquals(expectedAsset, returnedAsset);
    }

    @Test
    public void getSumTest() {
        Asset asset = Asset.builder().id(1L).assetName("Бумага").assetCount(12).assetNumber(21334).cost(7.50).build();
        Long id = 1L;
        int count = 2;

        when(assetRepository.findById(id)).thenReturn(Optional.of(asset));

        Double sum = assetService.getSum(id, count);
        assertEquals(15.0, sum);
    }

    @Test
    public void getNullWhenEmptyAssetTest() {
        Asset asset = Asset.builder().id(1L).assetName("Бумага").assetCount(12).assetNumber(21334).cost(7.50).build();
        Long id = 2L;
        int count = 2;

        when(assetRepository.findById(asset.getId())).thenReturn(Optional.of(asset));

        Double sum = assetService.getSum(id, count);
        assertNull(sum);
    }
}

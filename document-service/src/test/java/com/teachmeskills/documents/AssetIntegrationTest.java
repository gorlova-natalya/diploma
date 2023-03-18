package com.teachmeskills.documents;

import com.teachmeskills.documents.model.Asset;
import com.teachmeskills.documents.repository.AssetRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssetIntegrationTest extends AbstractIntegrationDocumentsTest {

    @Autowired
    private AssetRepository assetRepository;

    @Test
    public void getAssetByIdTest() {
        Long id = 1L;

        Optional<Asset> result = assetRepository.findById(id);

        assertEquals(result.get().getAssetName(), "Картридж для Pantum M6800");
    }
}

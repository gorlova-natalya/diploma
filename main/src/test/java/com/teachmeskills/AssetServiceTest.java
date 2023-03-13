package com.teachmeskills;

import com.teachmeskills.security.client.AssetClient;
import com.teachmeskills.security.service.AssetService;
import org.example.common.dto.document.AssetDto;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@Disabled
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = AssetService.class)
public class AssetServiceTest {

    @Autowired
    private AssetService assetService;

    @MockBean
    private AssetClient assetClient;

    @Test
    public void getAllAssetsTest() {
        List<AssetDto> assets = new ArrayList<>();

        given(assetClient.getAssets()).willReturn(assets);
        List<AssetDto> expected = assetService.getAssets();

        assertEquals(expected, assets);

        verify(assetClient).getAssets();
    }
}

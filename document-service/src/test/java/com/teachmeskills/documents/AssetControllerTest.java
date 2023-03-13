package com.teachmeskills.documents;

import com.teachmeskills.documents.controller.AssetsController;
import com.teachmeskills.documents.converter.AssetConverter;
import com.teachmeskills.documents.model.Asset;
import com.teachmeskills.documents.model.AssetUnit;
import com.teachmeskills.documents.model.Department;
import com.teachmeskills.documents.model.Employee;
import com.teachmeskills.documents.service.AssetService;
import org.example.common.dto.document.AssetDto;
import org.example.common.dto.document.AssetUnitDto;
import org.example.common.dto.document.DepartmentDto;
import org.example.common.dto.document.EmployeeDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(AssetsController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = AssetsController.class)
public class AssetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private AssetService assetService;

    @MockBean
    private AssetConverter assetConverter;

    @Test
    public void getAllAssetsTest() throws Exception {

        Asset newAsset = Asset.builder().id(1L).assetName("Бумага").assetCount(12).assetNumber(21345).cost(7.80)
                .department(new Department()).employee(new Employee()).assetUnits(new AssetUnit())
                .invoiceAssetCount(new ArrayList<>()).build();

        List<Asset> allAssets = List.of(newAsset);
        List<AssetDto> expectedAssets = allAssets.stream().map(asset -> new AssetDto(asset.getId(), asset.getAssetName(),
                        asset.getAssetCount(), asset.getAssetNumber(), asset.getCost(),
                        DepartmentDto.builder().build(),
                        EmployeeDto.builder().build(), new AssetUnitDto()))
                .collect(Collectors.toList());

        when(assetService.findAssets()).thenReturn(allAssets);
        when(assetConverter.toDto(allAssets)).thenReturn(expectedAssets);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/assets/all")
                        .content(String.valueOf(allAssets))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        then(assetService)
                .should()
                .findAssets();

        List<AssetDto> returnedAssets = mapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<List<AssetDto>>() {
                });
        assertEquals(expectedAssets, returnedAssets);
    }
}

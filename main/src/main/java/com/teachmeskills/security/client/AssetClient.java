package com.teachmeskills.security.client;

import org.example.common.dto.document.AssetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "domain3", url = "${services.document.url}/api/v1/assets")
public interface AssetClient {

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    List<AssetDto> getAssets();
}

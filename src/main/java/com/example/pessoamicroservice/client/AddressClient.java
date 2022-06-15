package com.example.pessoamicroservice.client;

import com.example.pessoamicroservice.client.dto.AddressResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(url = "${microservice.address.url}", name = "addressClient", fallback = AddressClientFallback.class)
public interface AddressClient {

    @GetMapping("list/{id}")
    List<AddressResponseDTO> getAddressFromClient(@PathVariable Long id);

    @Slf4j
    @Component
    class AddFallback implements AddressClient {

        @Override
        public List<AddressResponseDTO> getAddressFromClient(Long id) {
            log.info("fallback aqui");
            return new ArrayList<>();
        }
    }
}

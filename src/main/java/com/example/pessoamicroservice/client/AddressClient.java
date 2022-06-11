package com.example.pessoamicroservice.client;

import com.example.pessoamicroservice.client.dto.AddressResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "${microservice.address.url}", name = "addressClient")
public interface AddressClient {

    @GetMapping("list/{id}")
    List<AddressResponseDTO> getAddressFromClient(@PathVariable Long id);

}

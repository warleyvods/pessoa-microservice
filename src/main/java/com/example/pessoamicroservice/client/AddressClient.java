package com.example.pessoamicroservice.client;

import com.example.pessoamicroservice.client.dto.AddressResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Primary
@FeignClient(url = "${microservice.address.url}", name = "addressClient", fallback = AddressClientFallback.class)
public interface AddressClient {

    @GetMapping("list/{id}")
    List<AddressResponseDTO> getAddressFromClient(@PathVariable final Long id);

}

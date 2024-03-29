package com.example.pessoamicroservice.client;

import com.example.pessoamicroservice.client.dto.AddressResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class AddressClientFallback implements AddressClient {

    @Override
    public List<AddressResponseDTO> getAddressFromClient(final Long id) {
        log.info("fallback client");
        return Collections.emptyList();
    }
}

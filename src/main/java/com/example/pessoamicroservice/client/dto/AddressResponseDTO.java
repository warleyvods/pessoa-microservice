package com.example.pessoamicroservice.client.dto;

public record AddressResponseDTO(
        Long id,
        String street,
        String complement,
        String number,
        String zipCode,
        String district,
        String city,
        String country
) {
}

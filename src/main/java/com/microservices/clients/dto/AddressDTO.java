package com.microservices.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long idAddress;
    private String street;
    private String number;
    private String reference;
    private DistrictDTO district;
}

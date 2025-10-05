package com.microservices.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDTO {
    private Long idDistrict;
    private String name;
    private ProvinceDTO province;
}

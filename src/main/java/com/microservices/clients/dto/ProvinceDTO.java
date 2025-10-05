package com.microservices.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceDTO {
    private Long idProvince;
    private String name;
    private DepartmentDTO department;
}

package com.microservices.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long idClient;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String documentNumber;
    private DocumentTypeDTO documentType;
    private AddressDTO address;

}

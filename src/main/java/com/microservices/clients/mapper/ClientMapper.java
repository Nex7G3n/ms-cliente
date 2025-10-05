package com.microservices.clients.mapper;

import com.microservices.clients.dto.*;
import com.microservices.clients.model.*;
import com.microservices.clients.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientMapper {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    public ClientDTO toDto(Client client) {
        if (client == null) {
            return null;
        }
        return ClientDTO.builder()
                .idClient(client.getIdClient())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .documentNumber(client.getDocumentNumber())
                .documentType(toDto(client.getDocumentType()))
                .address(toDto(client.getAddress()))
                .build();
    }

    public Client toEntity(ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }
        Client client = new Client();
        client.setIdClient(clientDTO.getIdClient());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setDocumentNumber(clientDTO.getDocumentNumber());

        if (clientDTO.getDocumentType() != null && clientDTO.getDocumentType().getIdDocumentType() != null) {
            Optional<DocumentType> existingDocumentType = documentTypeRepository.findById(clientDTO.getDocumentType().getIdDocumentType());
            client.setDocumentType(existingDocumentType.orElseGet(() -> toEntity(clientDTO.getDocumentType())));
        } else if (clientDTO.getDocumentType() != null) {
            client.setDocumentType(toEntity(clientDTO.getDocumentType()));
        }

        if (clientDTO.getAddress() != null && clientDTO.getAddress().getIdAddress() != null) {
            Optional<Address> existingAddress = addressRepository.findById(clientDTO.getAddress().getIdAddress());
            client.setAddress(existingAddress.orElseGet(() -> toEntity(clientDTO.getAddress())));
        } else if (clientDTO.getAddress() != null) {
            client.setAddress(toEntity(clientDTO.getAddress()));
        }

        return client;
    }

    public DocumentTypeDTO toDto(DocumentType documentType) {
        if (documentType == null) {
            return null;
        }
        return DocumentTypeDTO.builder()
                .idDocumentType(documentType.getIdDocumentType())
                .name(documentType.getName())
                .description(documentType.getDescription())
                .build();
    }

    public DocumentType toEntity(DocumentTypeDTO documentTypeDTO) {
        if (documentTypeDTO == null) {
            return null;
        }
        DocumentType documentType = new DocumentType();
        documentType.setIdDocumentType(documentTypeDTO.getIdDocumentType());
        documentType.setName(documentTypeDTO.getName());
        documentType.setDescription(documentTypeDTO.getDescription());
        return documentType;
    }

    public AddressDTO toDto(Address address) {
        if (address == null) {
            return null;
        }
        return AddressDTO.builder()
                .idAddress(address.getIdAddress())
                .street(address.getStreet())
                .number(address.getNumber())
                .reference(address.getReference())
                .district(toDto(address.getDistrict()))
                .build();
    }

    public Address toEntity(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }
        Address address = new Address();
        address.setIdAddress(addressDTO.getIdAddress());
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setReference(addressDTO.getReference());

        if (addressDTO.getDistrict() != null && addressDTO.getDistrict().getIdDistrict() != null) {
            Optional<District> existingDistrict = districtRepository.findById(addressDTO.getDistrict().getIdDistrict());
            address.setDistrict(existingDistrict.orElseGet(() -> toEntity(addressDTO.getDistrict())));
        } else if (addressDTO.getDistrict() != null) {
            address.setDistrict(toEntity(addressDTO.getDistrict()));
        }
        return address;
    }

    public DistrictDTO toDto(District district) {
        if (district == null) {
            return null;
        }
        return DistrictDTO.builder()
                .idDistrict(district.getIdDistrict())
                .name(district.getName())
                .province(toDto(district.getProvince()))
                .build();
    }

    public District toEntity(DistrictDTO districtDTO) {
        if (districtDTO == null) {
            return null;
        }
        District district = new District();
        district.setIdDistrict(districtDTO.getIdDistrict());
        district.setName(districtDTO.getName());

        if (districtDTO.getProvince() != null && districtDTO.getProvince().getIdProvince() != null) {
            Optional<Province> existingProvince = provinceRepository.findById(districtDTO.getProvince().getIdProvince());
            district.setProvince(existingProvince.orElseGet(() -> toEntity(districtDTO.getProvince())));
        } else if (districtDTO.getProvince() != null) {
            district.setProvince(toEntity(districtDTO.getProvince()));
        }
        return district;
    }

    public ProvinceDTO toDto(Province province) {
        if (province == null) {
            return null;
        }
        return ProvinceDTO.builder()
                .idProvince(province.getIdProvince())
                .name(province.getName())
                .department(toDto(province.getDepartment()))
                .build();
    }

    public Province toEntity(ProvinceDTO provinceDTO) {
        if (provinceDTO == null) {
            return null;
        }
        Province province = new Province();
        province.setIdProvince(provinceDTO.getIdProvince());
        province.setName(provinceDTO.getName());

        if (provinceDTO.getDepartment() != null && provinceDTO.getDepartment().getIdDepartment() != null) {
            Optional<Department> existingDepartment = departmentRepository.findById(provinceDTO.getDepartment().getIdDepartment());
            province.setDepartment(existingDepartment.orElseGet(() -> toEntity(provinceDTO.getDepartment())));
        } else if (provinceDTO.getDepartment() != null) {
            province.setDepartment(toEntity(provinceDTO.getDepartment()));
        }
        return province;
    }

    public DepartmentDTO toDto(Department department) {
        if (department == null) {
            return null;
        }
        return DepartmentDTO.builder()
                .idDepartment(department.getIdDepartment())
                .name(department.getName())
                .build();
    }

    public Department toEntity(DepartmentDTO departmentDTO) {
        if (departmentDTO == null) {
            return null;
        }
        Department department = new Department();
        department.setIdDepartment(departmentDTO.getIdDepartment());
        department.setName(departmentDTO.getName());
        return department;
    }
}

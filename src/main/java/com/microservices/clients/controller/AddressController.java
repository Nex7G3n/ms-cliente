package com.microservices.clients.controller;

import com.microservices.clients.dto.AddressDTO;
import com.microservices.clients.mapper.ClientMapper;
import com.microservices.clients.model.Address;
import com.microservices.clients.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private ClientMapper clientMapper; // Reutilizamos ClientMapper para mapear Address

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        List<Address> addresses = addressService.findAllAddresses();
        List<AddressDTO> addressDTOs = addresses.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(addressDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        return addressService.findAddressById(id)
                .map(clientMapper::toDto)
                .map(addressDTO -> new ResponseEntity<>(addressDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
        Address address = clientMapper.toEntity(addressDTO);
        Address savedAddress = addressService.saveAddress(address);
        return new ResponseEntity<>(clientMapper.toDto(savedAddress), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        try {
            Address addressDetails = clientMapper.toEntity(addressDTO);
            Address updatedAddress = addressService.updateAddress(id, addressDetails);
            return new ResponseEntity<>(clientMapper.toDto(updatedAddress), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAddress(@PathVariable Long id) {
        try {
            addressService.deleteAddress(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

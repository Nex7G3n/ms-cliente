package com.microservices.clients.controller;

import com.microservices.clients.dto.DistrictDTO;
import com.microservices.clients.mapper.ClientMapper;
import com.microservices.clients.model.District;
import com.microservices.clients.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients/districts")
public class DistrictController {

    @Autowired
    private DistrictService districtService;
    @Autowired
    private ClientMapper clientMapper; // Reutilizamos ClientMapper para mapear District

    @GetMapping
    public ResponseEntity<List<DistrictDTO>> getAllDistricts() {
        List<District> districts = districtService.findAllDistricts();
        List<DistrictDTO> districtDTOs = districts.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(districtDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistrictDTO> getDistrictById(@PathVariable Long id) {
        return districtService.findDistrictById(id)
                .map(clientMapper::toDto)
                .map(districtDTO -> new ResponseEntity<>(districtDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<DistrictDTO> createDistrict(@RequestBody DistrictDTO districtDTO) {
        District district = clientMapper.toEntity(districtDTO);
        District savedDistrict = districtService.saveDistrict(district);
        return new ResponseEntity<>(clientMapper.toDto(savedDistrict), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DistrictDTO> updateDistrict(@PathVariable Long id, @RequestBody DistrictDTO districtDTO) {
        try {
            District districtDetails = clientMapper.toEntity(districtDTO);
            District updatedDistrict = districtService.updateDistrict(id, districtDetails);
            return new ResponseEntity<>(clientMapper.toDto(updatedDistrict), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDistrict(@PathVariable Long id) {
        try {
            districtService.deleteDistrict(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

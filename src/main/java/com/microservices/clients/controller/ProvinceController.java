package com.microservices.clients.controller;

import com.microservices.clients.dto.ProvinceDTO;
import com.microservices.clients.mapper.ClientMapper;
import com.microservices.clients.model.Province;
import com.microservices.clients.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private ClientMapper clientMapper; // Reutilizamos ClientMapper para mapear Province

    @GetMapping
    public ResponseEntity<List<ProvinceDTO>> getAllProvinces() {
        List<Province> provinces = provinceService.findAllProvinces();
        List<ProvinceDTO> provinceDTOs = provinces.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(provinceDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinceDTO> getProvinceById(@PathVariable Long id) {
        return provinceService.findProvinceById(id)
                .map(clientMapper::toDto)
                .map(provinceDTO -> new ResponseEntity<>(provinceDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ProvinceDTO> createProvince(@RequestBody ProvinceDTO provinceDTO) {
        Province province = clientMapper.toEntity(provinceDTO);
        Province savedProvince = provinceService.saveProvince(province);
        return new ResponseEntity<>(clientMapper.toDto(savedProvince), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProvinceDTO> updateProvince(@PathVariable Long id, @RequestBody ProvinceDTO provinceDTO) {
        try {
            Province provinceDetails = clientMapper.toEntity(provinceDTO);
            Province updatedProvince = provinceService.updateProvince(id, provinceDetails);
            return new ResponseEntity<>(clientMapper.toDto(updatedProvince), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProvince(@PathVariable Long id) {
        try {
            provinceService.deleteProvince(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

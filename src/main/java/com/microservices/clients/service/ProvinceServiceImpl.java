package com.microservices.clients.service;

import com.microservices.clients.model.Province;
import com.microservices.clients.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<Province> findAllProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    public Optional<Province> findProvinceById(Long id) {
        return provinceRepository.findById(id);
    }

    @Override
    public Province saveProvince(Province province) {
        return provinceRepository.save(province);
    }

    @Override
    public Province updateProvince(Long id, Province provinceDetails) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Province not found with id " + id));
        province.setName(provinceDetails.getName());
        province.setDepartment(provinceDetails.getDepartment());
        return provinceRepository.save(province);
    }

    @Override
    public void deleteProvince(Long id) {
        provinceRepository.deleteById(id);
    }
}

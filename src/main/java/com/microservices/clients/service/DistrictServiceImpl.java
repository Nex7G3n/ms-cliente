package com.microservices.clients.service;

import com.microservices.clients.model.District;
import com.microservices.clients.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<District> findAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public Optional<District> findDistrictById(Long id) {
        return districtRepository.findById(id);
    }

    @Override
    public District saveDistrict(District district) {
        return districtRepository.save(district);
    }

    @Override
    public District updateDistrict(Long id, District districtDetails) {
        District district = districtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("District not found with id " + id));
        district.setName(districtDetails.getName());
        district.setProvince(districtDetails.getProvince());
        return districtRepository.save(district);
    }

    @Override
    public void deleteDistrict(Long id) {
        districtRepository.deleteById(id);
    }
}

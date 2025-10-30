package com.microservices.clients.service;

import com.microservices.clients.model.District;
import java.util.List;
import java.util.Optional;

public interface DistrictService {
    List<District> findAllDistricts();
    Optional<District> findDistrictById(Long id);
    District saveDistrict(District district);
    District updateDistrict(Long id, District districtDetails);
    void deleteDistrict(Long id);
}

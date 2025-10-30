package com.microservices.clients.service;

import com.microservices.clients.model.Province;
import java.util.List;
import java.util.Optional;

public interface ProvinceService {
    List<Province> findAllProvinces();
    Optional<Province> findProvinceById(Long id);
    Province saveProvince(Province province);
    Province updateProvince(Long id, Province provinceDetails);
    void deleteProvince(Long id);
}

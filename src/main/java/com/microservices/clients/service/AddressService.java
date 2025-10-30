package com.microservices.clients.service;

import com.microservices.clients.model.Address;
import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<Address> findAllAddresses();
    Optional<Address> findAddressById(Long id);
    Address saveAddress(Address address);
    Address updateAddress(Long id, Address addressDetails);
    void deleteAddress(Long id);
}

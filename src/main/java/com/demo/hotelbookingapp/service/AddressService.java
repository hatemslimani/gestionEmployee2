package com.demo.hotelbookingapp.service;

import com.demo.hotelbookingapp.model.Address;
import com.demo.hotelbookingapp.model.dto.AddressDTO;

public interface AddressService {

    Address saveAddress(AddressDTO addressDTO);

    AddressDTO findAddressById(Long id);

    Address updateAddress(AddressDTO addressDTO);

    void deleteAddress(Long id);

    Address mapAddressDtoToAddress(AddressDTO dto);

    AddressDTO mapAddressToAddressDto(Address address);


}

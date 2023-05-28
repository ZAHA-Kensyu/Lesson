package com.example.lesson.Service;

import com.example.lesson.Exception.NoSuchPostalCodeException;

public interface AddressService {
    String searchAddressFromPostalCode(String postalCode) throws NoSuchPostalCodeException;
}

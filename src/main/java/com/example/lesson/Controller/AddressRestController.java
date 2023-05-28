package com.example.lesson.Controller;

import com.example.lesson.Exception.NoSuchPostalCodeException;
import com.example.lesson.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AddressRestController {
    @Autowired
    AddressService addressService;

    private record AddressInfo(String postalCode,String address){};

    @GetMapping("search-address")
    public AddressInfo search(@RequestParam(name= "postalCode")String postalCode){
        System.out.println("RestAddressController search2呼ばれました");
        try {
            var address = addressService.searchAddressFromPostalCode(postalCode);
            return new AddressInfo(postalCode, address);
        } catch (NoSuchPostalCodeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}

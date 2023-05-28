package com.example.lesson.Service;

import com.example.lesson.Exception.NoSuchPostalCodeException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService{
    private final Map<String,String> addressMap = Map.ofEntries(
            Map.entry("120-0012","東京都 足立区 青い(1~3丁目)"),
            Map.entry("900-0001","沖縄県 那覇市 港町")
    );

    @Override
    public String searchAddressFromPostalCode(String postalCode) throws NoSuchPostalCodeException{
        var address = addressMap.get(postalCode);
        if(address == null){
            throw new NoSuchPostalCodeException();
        }
        return address;
    }
}

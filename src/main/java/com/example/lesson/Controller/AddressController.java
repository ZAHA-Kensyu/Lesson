package com.example.lesson.Controller;

import com.example.lesson.Exception.NoSuchPostalCodeException;
import com.example.lesson.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping("/search")
    public String search(Model model){
        return "search";
    }

    @GetMapping("/searchResult")
    public String searchResult(@RequestParam(name="postal") String postalCode,Model model){
        if(postalCode != null && !postalCode.trim().isEmpty()){
            model.addAttribute("postalCode",postalCode);
            try{
                var address = addressService.searchAddressFromPostalCode(postalCode);
                model.addAttribute("address",address);
            }catch (NoSuchPostalCodeException e){
                model.addAttribute("address","入力された値が見つかりません");
            }
        }else{
            model.addAttribute("isResultDisplay",false);
        }
        return "search";
    }

    @GetMapping("/search2")
    public String search2(){
        System.out.println("AddressController search2呼ばれました");
        return "search2";
    }
}

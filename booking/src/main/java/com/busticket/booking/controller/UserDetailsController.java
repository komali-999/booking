package com.busticket.booking.controller;

import com.busticket.booking.model.UserDetails;
import com.busticket.booking.service.UserService;
import com.busticket.booking.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserDetailsController {
    @Autowired
    ValidationService validationService;
    @Autowired
    UserService userService;
    @PostMapping("/create")
    public ResponseEntity user(@RequestBody UserDetails details) throws NoSuchFieldException, IllegalAccessException {
       ResponseEntity isValid=validationService.validateUserInfo(details);
     if(isValid.getStatusCode().is2xxSuccessful()){
         return userService.createUser(details);
     }
     return isValid;
    }

    @GetMapping("/userdetails")
    public List<UserDetails> getCustomerDetails(@RequestParam String number ) throws NoSuchFieldException, IllegalAccessException {
       List<UserDetails>  details = userService.getUserDetailsByPhonumber(number);
        userService.convertToMap(details);
        return details;
    }
}
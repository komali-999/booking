package com.busticket.booking.service;

import com.busticket.booking.model.UserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationService {
    public ResponseEntity validateUserInfo(UserDetails userDetails){
       if(!StringUtils.isNotEmpty(userDetails.getFirstName())||!StringUtils.isNotEmpty(userDetails.getLastName())||!isPhoneNumber(userDetails.getPhoneNumber()) || !isCorrectAge(userDetails.getAge()))  {
          return ResponseEntity.badRequest().body("Please check the input fields");
       } else { return ResponseEntity.ok().body("All fields are valid");}
    }
    public boolean isPhoneNumber(String number){
        Pattern p = Pattern.compile("^\\d{10}$");
        return Optional.ofNullable(number).filter(n-> p.matcher(number).matches()).map(m -> true).orElse(false);
    }
    public boolean isCorrectAge(String number){
     Pattern p = Pattern.compile("^0*(?:[1-9][0-9]?|100)$") ;
    return  Optional.ofNullable(number).filter(n->p.matcher(number).matches()).map(m -> true).orElse(false);
    }
}

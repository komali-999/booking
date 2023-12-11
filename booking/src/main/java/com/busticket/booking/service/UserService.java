package com.busticket.booking.service;

import com.busticket.booking.model.EmailRequest;
import com.busticket.booking.model.UserDetails;
import com.busticket.booking.repository.UserRepository;
import org.apache.coyote.Response;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.sql.Array;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    private List<UserDetails> details;


    @Autowired
    ExcelService excelService;
    public ResponseEntity createUser(UserDetails details)  {
        List<UserDetails> exists = this.getUserDetailsByPhonumber(details.getPhoneNumber());
        List<UserDetails> ageExists =userRepository.findByAge(details.getAge());
        if (exists.size() == 0) {
            details.setCreated_timestamp(Timestamp.valueOf(LocalDateTime.now()));
            userRepository.save(details);
            return ResponseEntity.ok().body("User Details created SuccessFully");
        }
        return ResponseEntity.badRequest().body(exists.get(0).getFirstName() + " User Details Alredy Exists");
    }


    public List<UserDetails> getUserDetailsByPhonumber(String phoneNumber)  {
      return  userRepository.findByPhoneNumber(phoneNumber);

    }





    public void convertToMap(List<UserDetails> details) throws NoSuchFieldException, IllegalAccessException {
        Map<String, String> hm = new HashMap();
        for (UserDetails detail : details) {
            Field[] field = detail.getClass().getDeclaredFields();

            for (Field loop : field) {
                loop.setAccessible(true);
                String key = loop.getName();
                String value = loop.get(detail).toString();
                System.out.println(key + "and the value is " + value);
                hm.put(key, value);
            }
        }
        excelService.createExcel(hm);
    }


}

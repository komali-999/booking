package com.busticket.booking.repository;

import com.busticket.booking.model.UserDetails;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface UserRepository extends JpaRepository<UserDetails,Integer> {
    List<UserDetails> findByPhoneNumber(String number);
    List<UserDetails> findByAge(String age);
}



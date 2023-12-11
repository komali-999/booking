package com.busticket.booking.repository;

import com.busticket.booking.model.ReservationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReservationRepository extends JpaRepository<ReservationDetails, Integer> {
List<ReservationDetails> findByBookingId(String bookingId);
List<ReservationDetails> findByPhonenumber(String phonenumber);
}

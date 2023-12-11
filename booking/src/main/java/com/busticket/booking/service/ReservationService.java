package com.busticket.booking.service;


import com.busticket.booking.model.EmailRequest;
import com.busticket.booking.model.ReservationDetails;
import com.busticket.booking.model.UserDetails;
import com.busticket.booking.repository.ReservationRepository;
import com.busticket.booking.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepo;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;



    public ResponseEntity createReservation(ReservationDetails reservationDetails) {
        reservationDetails.setCreated_timestamp(Timestamp.valueOf(LocalDateTime.now()));
        reservationDetails.setTravelDate(Date.valueOf(LocalDate.now()));
        String bookingId = String.valueOf(new Random().nextInt(10000));
        reservationDetails.setBookingId(bookingId);
        List<UserDetails> isUserFound = userRepository.findByPhoneNumber(reservationDetails.getPhonenumber());
        UserDetails firstUser;
        if (isUserFound.size() > 0) {
            firstUser = isUserFound.stream().findFirst().get();
            userRepository.save(firstUser.toBuilder().bookingId(bookingId).build());
        } else {
            return ResponseEntity.badRequest().body("User Not Found, Create new User");
        }
        reservationDetails.setStatus("Booked");
        reservationRepo.save(reservationDetails);
        emailService.sendEmail(EmailRequest.builder().from("BusReservation@gmail.com").to(firstUser.getEmailAddress()).subject(new StringBuilder().append("Congrats!!! Your Reservation is Booked from ").append(reservationDetails.fromAddress).append(" to ").append(reservationDetails.getToAddress()).toString()).build());
        return ResponseEntity.ok().body("Congrats! your Reseravation is created");
    }

//    public ResponseEntity cancelReservation(String bookingId) {
//
//        List<ReservationDetails> reservation = reservationRepo.findByBookingId(bookingId);
//
//        if (reservation.size() > 0) {
//            reservationRepo.save(reservation.stream().findFirst().get().toBuilder().status("Cancelled").updated_reservation(Timestamp.valueOf(LocalDateTime.now())).build());
//        } else {
//            ;
//            return ResponseEntity.badRequest().body("No booking found to cancel for the user");
//        }
//        return ResponseEntity.ok().body("Your reservation is cancelled");
//    }

    public ResponseEntity updateReservation(ReservationDetails reservationDetails) {
        List<UserDetails> isUserFound = userRepository.findByPhoneNumber(reservationDetails.getPhonenumber());
        if (isUserFound.size() > 0) {
            UserDetails first = isUserFound.stream().findFirst().get();
            ReservationDetails reservation = reservationRepo.findByBookingId(first.getBookingId()).stream().findFirst().orElse(null);
            if (reservationDetails.getToAddress() != null) {
                reservation.setToAddress(reservationDetails.getToAddress());
            }
            reservation.setUpdated_reservation(Timestamp.valueOf(LocalDateTime.now()));

            if (reservationDetails.getTravelDate() != null) {
                reservation.setTravelDate(reservationDetails.getTravelDate());
            }
            if (reservationDetails.getFromAddress() != null) {
                reservation.setToAddress(reservationDetails.getFromAddress());
            }
            reservationRepo.save(reservation);
            //compareObjects(reservation,reservationDetails);

        } else {
            return ResponseEntity.badRequest().body("No user found");
        }
        return ResponseEntity.ok().body("Congrats! your Reseravation is updated");
    }

    public ResponseEntity getAllReservations(String phonenumber, String status) {
        List<UserDetails> user = userRepository.findByPhoneNumber(phonenumber);
        if (user.size() > 0) {
            List<ReservationDetails> reservations = reservationRepo.findByPhonenumber(phonenumber).stream().filter(r -> r.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
        } else {
            return ResponseEntity.badRequest().body("No user found ");
        }
        return ResponseEntity.ok().body("Congrats! your reservations are below");
    }

    private void compareObjects(ReservationDetails reservation, ReservationDetails reservationDetails) {
        Field[] obj1 = reservation.getClass().getDeclaredFields();
        Field[] obj2 = reservationDetails.getClass().getDeclaredFields();
        try {
            for (Field obj1ColName : obj1) {
                obj1ColName.setAccessible(true);
                String fieldName = obj1ColName.getName();
                String obj1fieldValue = obj1ColName.get(reservation) != null ? (String) obj1ColName.get(reservation) : "";
                String obj2FieldValue = reservationDetails.getClass().getField(fieldName).toString();
                if (obj1fieldValue != obj2FieldValue) {
                    Field f = reservation.getClass().getDeclaredField(fieldName);
                    f.setAccessible(true);
                    f.set(reservation, obj2FieldValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



package com.busticket.booking.controller;

import com.busticket.booking.model.ReservationDetails;
import com.busticket.booking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @PostMapping("/createreservation")
    public ResponseEntity reservationDetails(@RequestBody ReservationDetails reservationDetails) {
        ResponseEntity isValid = reservationService.createReservation(reservationDetails);
        if (isValid.getStatusCode().is2xxSuccessful()) {
           return  isValid;
        } else {
           return  ResponseEntity.badRequest().body("Unable to create the reservation");
        }

    }

     @PatchMapping("/update")
      public ResponseEntity updateReservation(@RequestBody ReservationDetails reservationDetails){
      ResponseEntity isUpdated = reservationService.updateReservation(reservationDetails);
        if (isUpdated.getStatusCode().is2xxSuccessful()) {
            ResponseEntity.ok().body("Upadated the reservation");
        }
        return isUpdated;
    }

    @DeleteMapping("/cancel")
    public ResponseEntity cancelReservation(@RequestParam String bookingId){
        ResponseEntity isCancelled = reservationService.cancelReservation(bookingId);
        if(isCancelled.getStatusCode().is2xxSuccessful()){
            ResponseEntity.ok().body("Sorry to Go, Your reservation is Cancelled");
        }
        return isCancelled;
    }

    @GetMapping("/get")
    public ResponseEntity getReservation(@RequestParam String phoneNumber, @RequestParam String status  ){
        ResponseEntity isReservation = reservationService.getAllReservations(phoneNumber, status);
        if(isReservation.getStatusCode().is2xxSuccessful()){
            ResponseEntity.ok().body("Your reservations are fetched");
        }
        return isReservation;
    }
}

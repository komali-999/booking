package com.busticket.booking.model;

import javax.persistence.*;



import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name="reservationdetails", schema="reservation")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetails {
   // @Column(name = "from_address")
//   @NotEmpty(message  = "Hi Icannot be empty")
    public String fromAddress;
//    @NotEmpty(message="should not be empty")
    private String toAddress;
//    @NotEmpty(message="should not be empty")
    private String status;
    private String phonenumber;
    Date travelDate;
    public String getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    private String bookingId;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    Timestamp created_timestamp;
    Timestamp updated_reservation;

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public int getId() {
        return id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Timestamp getUpdated_reservation() {
        return updated_reservation;
    }

    public void setUpdated_reservation(Timestamp updated_reservation) {
        this.updated_reservation = updated_reservation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreated_timestamp() {
        return created_timestamp;
    }

    public void setCreated_timestamp(Timestamp created_timestamp) {
        this.created_timestamp = created_timestamp;
    }


}

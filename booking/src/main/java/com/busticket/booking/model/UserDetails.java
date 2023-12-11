package com.busticket.booking.model;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;


@Entity
@Builder(toBuilder = true)
@Table(name = "userinfo",schema = "reservation")
@AllArgsConstructor
public class UserDetails {

    @Column(name = "firstname")
    private String firstName;

    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    private String bookingId;
    @Column(name = "lastname")
    private String lastName;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    @Column(name = "age")
    private String age;

    public UserDetails() {
    }

    public Timestamp getCreated_timestamp() {
        return created_timestamp;
    }

    public void setCreated_timestamp(Timestamp created_timestamp) {
        this.created_timestamp = created_timestamp;
    }
    @Column(name = "created_timestamp")
    Timestamp created_timestamp;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }




    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   // @JsonProperty("phoneNumber")
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

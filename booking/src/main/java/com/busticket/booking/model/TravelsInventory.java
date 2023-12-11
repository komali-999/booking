package com.busticket.booking.model;


import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name="travelinventory",schema = "reservation")
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TravelsInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String busTime;

    private String busCondition;


    @Column(name = "busName")
    private String busName;

    private String fromAddress;

    private String busType;

    private String toAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String busNumber;

    public String getBusTime() {
        return busTime;
    }

    public void setBusTime(String busTime) {
        this.busTime = busTime;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }


    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getBusCondition() {
        return busCondition;
    }

    public void setBusCondition(String busCondition) {
        this.busCondition = busCondition;
    }
}
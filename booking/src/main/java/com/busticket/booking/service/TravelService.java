package com.busticket.booking.service;

import com.busticket.booking.model.TravelsInventory;
import com.busticket.booking.repository.TravelInventoryRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TravelService {

    @Autowired
    TravelInventoryRepository travelInventoryRepository;

    public ResponseEntity TravelInventoryDetails(TravelsInventory travelsInventory) {
        if(StringUtils.isNotEmpty(travelsInventory.getBusName())||StringUtils.isNotEmpty(travelsInventory.getBusNumber())||StringUtils.isNotEmpty(travelsInventory.getBusTime()))  {
         List<TravelsInventory> bus =    travelInventoryRepository.findByBusNumber(travelsInventory.getBusNumber());
         if(bus.size()>0){
             return ResponseEntity.badRequest().body("Bus already exists");
         } else{
             travelInventoryRepository.save(travelsInventory);
         }
        } else { return ResponseEntity.badRequest().body("Bad request");}
        return ResponseEntity.ok().body("All fields are valid");
    }

    //public ResponseEntity updateTravelInventory(TravelsInventory travelsInventory){

 public ResponseEntity getAllBusesByRoute (String fromAddress,String toAddress, String busCondition) {
     List<TravelsInventory> buses = travelInventoryRepository.findByFromAddressAndToAddressIgnoreCase(fromAddress, toAddress);
     //method 1
     //List<TravelsInventory> goodBuses = buses.stream().filter(e->e.getBusCondition().equalsIgnoreCase(busCondition)).collect(Collectors.toList());
     // Method 2
     Map<String, List<TravelsInventory>> test = buses.stream().collect(Collectors.groupingBy(TravelsInventory::getBusCondition));
     return ResponseEntity.ok().body("Your search results are");
 }

  public ResponseEntity updatetravelInventory(TravelsInventory travelsInventory) {
      List<TravelsInventory> bus = travelInventoryRepository.findByBusNumber(travelsInventory.getBusNumber());
      bus.stream().map(e->e.toBuilder().busName(travelsInventory.getBusName()).build()).collect(Collectors.toList());
      if (bus.size() > 0) {

          TravelsInventory firstBus = bus.stream().findFirst().get();
          //Merthoid 1
          firstBus.toBuilder().busType(travelsInventory.getBusType()).fromAddress(travelsInventory.getFromAddress()).toAddress(travelsInventory.getToAddress()).busTime(travelsInventory.getBusTime()).busName(travelsInventory.getBusName()).build();
          //Method2 below
//          firstBus.setFromAddress(travelsInventory.getFromAddress());
//          firstBus.setToAddress(travelsInventory.getToAddress());
//          firstBus.setBusTime(travelsInventory.getBusTime());
//          firstBus.setBusType(travelsInventory.getBusType());
//          firstBus.setBusName(travelsInventory.getBusName());
          travelInventoryRepository.save(firstBus);
      } else {
          return ResponseEntity.badRequest().body("No bus found with the number");
      }
      return ResponseEntity.ok().body("bus is updated ");
  }

  public ResponseEntity cancelbus(String busNumber){
        List<TravelsInventory> repair = travelInventoryRepository.findByBusNumber(busNumber);
        if(repair.size()>0){
            travelInventoryRepository.save(repair.stream().findFirst().get().toBuilder().busCondition("Maintanence").build());
        }else {
            return ResponseEntity.badRequest().body("Bus Number not found");
        }
        return ResponseEntity.ok().body("BUS is under maintanence");
  }

  public ResponseEntity runningBuses(String busCondition){
        List<TravelsInventory> con = travelInventoryRepository.findByBusCondition(busCondition);
        if(con.size()>0){
            ResponseEntity.ok().body("Your buses are here");
        } else {
            ResponseEntity.badRequest().body("Your request is bad");
        }
        return ResponseEntity.ok().body("Running Busses list");
  }
}

package com.busticket.booking.controller;

import com.busticket.booking.model.TravelsInventory;
import com.busticket.booking.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/inventory")
@RestController
public class TravelInventoryController {
    @Autowired
    TravelService travelService;

    @PostMapping("/createinventory")
    public ResponseEntity createInventory(@RequestBody TravelsInventory travelsInventory) {
        ResponseEntity isValid = travelService.TravelInventoryDetails(travelsInventory);
        if (isValid.getStatusCode().is2xxSuccessful()) {
            ResponseEntity.ok().body("HEY CORRECT DETAILS");
        }
        return isValid;
    }
    @GetMapping("/getBusByRoute")
    public ResponseEntity getBusesByRoute(@RequestParam String fromAddress, String toAddress, String busCondition){
        ResponseEntity isvalid = travelService.getAllBusesByRoute(fromAddress, toAddress, busCondition);
    if (isvalid.getStatusCode().is2xxSuccessful()) {
        ResponseEntity.ok().body("HEY CORRECT DETAILS");
    }
    return isvalid;
}

@PutMapping("/updateInventory")
public ResponseEntity updateBus(@RequestBody TravelsInventory travelsInventory){
        ResponseEntity isValid = travelService.updatetravelInventory(travelsInventory);
        if(isValid.getStatusCode().is2xxSuccessful()){
            ResponseEntity.ok().body("Updated Bus");
        }  return isValid;
}

     @DeleteMapping("/busMaintanence")
    public ResponseEntity busCancel(@RequestParam String busNumber){
        ResponseEntity isvalid = travelService.cancelbus(busNumber);
        if(isvalid.getStatusCode().is2xxSuccessful()){
            ResponseEntity.ok().body("Bus Is Canceled ");
        }return isvalid;
     }

     @GetMapping("/goodBuses")
    public void goodBus(@RequestParam String busCondition){
        ResponseEntity isvalid = travelService.runningBuses(busCondition);
        if(isvalid.getStatusCode().is2xxSuccessful()){
            ResponseEntity.ok().body("good busses list");
        }
     }
}











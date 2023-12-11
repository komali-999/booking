package com.busticket.booking.repository;

import com.busticket.booking.model.TravelsInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelInventoryRepository extends JpaRepository<TravelsInventory, Integer> {

    List<TravelsInventory> findByFromAddressAndToAddressIgnoreCase(String fromAddress, String toAddress);

    List<TravelsInventory>findByBusNumber(String busNumber);

    List<TravelsInventory>findByBusCondition(String busCondition);
}

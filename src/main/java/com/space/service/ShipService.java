package com.space.service;

import com.space.model.Ship;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShipService {
    Ship getById(Long id);
    List<Ship> getAll(ShipSpecification specification);
    List<Ship> getAll(ShipSpecification specification, Pageable pageable);
    Ship createShip(ShipParameters shipParameters);
    Ship updateShip(ShipParameters shipParameters, Long id);
    void deleteShip(Long id);
}

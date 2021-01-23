package com.space.service;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ShipServiceImpl implements ShipService{
    private ShipRepository repository;

    @Autowired
    public void setRepository(ShipRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Ship getById(Long id) {
        if (id <= 0)
            throw new ShipException(HttpStatus.BAD_REQUEST);
        return repository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ship> getAll(ShipSpecification specification) {
        return repository.findAll(specification);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ship> getAll(ShipSpecification specification, Pageable pageable) {
        return repository.findAll(specification, pageable).getContent();
    }

    @Override
    @Transactional
    public Ship createShip(ShipParameters shipParameters) {
        if (shipParameters.getUsed() == null)
            shipParameters.setUsed(false);
        shipParameters.setSpeed(new BigDecimal(shipParameters.getSpeed()).setScale(2, RoundingMode.HALF_DOWN).doubleValue());
        if (!shipParameters.checkFieldsForCreate())
            throw new ShipException(HttpStatus.BAD_REQUEST);
        Ship ship = new Ship(shipParameters);
        repository.save(ship);
        return ship;
    }

    @Override
    @Transactional
    public Ship updateShip(ShipParameters shipParameters, Long id) {
        Ship ship = getById(id);
        if (!shipParameters.checkFieldsForUpdate())
            throw new ShipException(HttpStatus.BAD_REQUEST);
        ship.updateShip(shipParameters);
        repository.save(ship);
        return ship;
    }

    @Override
    @Transactional
    public void deleteShip(Long id) {
        Ship ship = getById(id);
        repository.delete(ship);
    }
}

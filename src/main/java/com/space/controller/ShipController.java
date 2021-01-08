package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipSearch;
import com.space.model.ShipType;
import com.space.service.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/rest/ships")
public class ShipController {
    private ShipService service;

    @Autowired
    public void setService(ShipService service) {
        this.service = service;
    }

    @GetMapping
    public List<Ship> getShips(@ModelAttribute ShipSearch ship, @RequestParam(required = false) ShipOrder order,
                               @RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "3") Integer pageSize) {
        ShipSpecification specification = new ShipSpecification(ship);
        Pageable pageable;
        if (order != null)
             pageable = PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName()));
        else
            pageable = PageRequest.of(pageNumber, pageSize);
        return service.getAll(specification, pageable);
    }

    @GetMapping(value = "/{id}")
    public Ship getShipsById(@PathVariable String id) {
        Ship ship = service.getById(Long.parseLong(id));
        return ship;
    }

    @GetMapping(value = "/count")
    public Integer getShipsCount(@ModelAttribute ShipSearch ship){
        ShipSpecification specification = new ShipSpecification(ship);
        return service.getAll(specification).size();
    }

    @PostMapping
    public Ship createShip(@RequestBody ShipParameters shipParameters) {
        Ship ship = service.createShip(shipParameters);
        return ship;
    }

    @PostMapping(value = "/{id}")
    public Ship updateShip(@RequestBody ShipParameters shipParameters, @PathVariable String id) {
        Ship ship = service.updateShip(shipParameters, Long.parseLong(id));
        return ship;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteShip(@PathVariable String id) {
        service.deleteShip(Long.parseLong(id));
    }

    @ExceptionHandler
    public ResponseEntity badRequestException(Exception exc) {
        ResponseEntity response;
        if (exc instanceof ShipException)
            response = new ResponseEntity(exc.getMessage(), ((ShipException) exc).getHttpStatus());
        else if (exc instanceof NoSuchElementException)
            response = new ResponseEntity(exc.getMessage(), HttpStatus.NOT_FOUND);
        else
            response = new ResponseEntity(exc.getMessage(), HttpStatus.BAD_REQUEST);
        return response;
    }

}

package com.space.service;

import com.space.model.ShipType;

import java.util.Calendar;
import java.util.Date;

public class ShipParameters {
    private String name, planet;
    private ShipType shipType;
    private Long prodDate;
    private Boolean isUsed;
    private Double speed;
    private Integer crewSize;
    private final int nameLength = 50;
    private final int planetLength = 50;
    private final long minProdDate = 26192235600000L;
    private final long maxProdDate = 33134734799000L;
    private final double minSpeed = 0.01;
    private final double maxSpeed = 0.99;
    private final int minCrewSize = 1;
    private final int maxCrewSize = 9999;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Long getProdDate() {
        return prodDate;
    }

    public void setProdDate(Long prodDate) {
        this.prodDate = prodDate;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }


    public boolean checkFieldsForCreate(){
        if (getName() == null || getPlanet() == null || getShipType() == null
                || getProdDate() == null || getSpeed() == null || getCrewSize() == null)
            return false;
        if (getName().length() > nameLength || getPlanet().length() > planetLength || getName().isEmpty()
                || getPlanet().isEmpty() || getProdDate() < minProdDate || getProdDate() > maxProdDate
                || getSpeed() < minSpeed || getSpeed() > maxSpeed || getCrewSize() < minCrewSize || getCrewSize() > maxCrewSize)
            return false;

        return true;
    }

    public boolean checkFieldsForUpdate(){
        if (getName() != null){
            if (getName().isEmpty() || getName().length() > nameLength)
                return false;
        }
        if (getPlanet() != null){
            if (getPlanet().isEmpty() || getPlanet().length() > planetLength)
                return false;
        }
        if (getProdDate() != null){
            if (getProdDate() < minProdDate || getProdDate() > maxProdDate)
                return false;
        }
        if (getSpeed() != null){
            if (getSpeed() < minSpeed || getSpeed() > maxSpeed)
                return false;
        }
        if (getCrewSize() != null){
            if (getCrewSize() < minCrewSize || getCrewSize() > maxCrewSize)
                return false;
        }

        return true;
    }
}

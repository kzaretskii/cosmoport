package com.space.model;

import com.space.service.ShipParameters;

import javax.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

import static javax.persistence.EnumType.STRING;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String planet;

    @Enumerated(STRING)
    private ShipType shipType;
    private Date prodDate;
    private Boolean isUsed;
    private Double speed;
    private Integer crewSize;
    private Double rating;

    public Ship() {
    }

    public Ship(ShipParameters shipParameters) {
        this.name = shipParameters.getName();
        this.planet = shipParameters.getPlanet();
        this.shipType = shipParameters.getShipType();
        this.isUsed = shipParameters.getUsed();
        this.speed = shipParameters.getSpeed();
        this.crewSize = shipParameters.getCrewSize();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(shipParameters.getProdDate());
        this.prodDate = calendar.getTime();
        this.rating = getCalculateRating();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getCalculateRating(){
        return new BigDecimal((80 * getSpeed() * (getUsed() ? 0.5 : 1)) / (getCurrentYear() - getProdYear() + 1))
                .setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private Integer getCurrentYear(){
        return 3019;
    }

    private Integer getProdYear(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(prodDate);
        return calendar.get(Calendar.YEAR);
    }

    public void updateShip(ShipParameters shipParameters){
        if (shipParameters.getName() != null)
            setName(shipParameters.getName());
        if (shipParameters.getPlanet() != null)
            setPlanet(shipParameters.getPlanet());
        if (shipParameters.getShipType() != null)
            setShipType(shipParameters.getShipType());
        if (shipParameters.getUsed() != null)
            setUsed(shipParameters.getUsed());
        if (shipParameters.getSpeed() != null)
            setSpeed(shipParameters.getSpeed());
        if (shipParameters.getCrewSize() != null)
            setCrewSize(shipParameters.getCrewSize());
        if (shipParameters.getProdDate() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(shipParameters.getProdDate());
            setProdDate(calendar.getTime());
        }
        setRating(getCalculateRating());
    }
}

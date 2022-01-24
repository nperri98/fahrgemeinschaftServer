package com.nperri.fahrgemeinschaftServer.entity;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="carpools")
public class Carpool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carpoolID;

    private String driver;
    private String driverEmail;
    private String departurePointCity;
    private Integer departurePointCityCode;

    public Integer getCarpoolID() {
        return carpoolID;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getDeparturePointCity() {
        return departurePointCity;
    }

    public void setDeparturePointCity(String departurePointCity) {
        this.departurePointCity = departurePointCity;
    }

    public Integer getDeparturePointCityCode() {
        return departurePointCityCode;
    }

    public void setDeparturePointCityCode(Integer departurePointCityCode) {
        this.departurePointCityCode = departurePointCityCode;
    }
}

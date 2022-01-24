package com.nperri.fahrgemeinschaftServer.entity;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="carpools")
public class Carpool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer carpoolID;
    private String driver;
    private Integer maxPassangers;
    private String departurePointCity;
    private Integer departurePointCityCode;


    @ManyToMany()
    @JoinTable(name = "carpool_user",
            joinColumns =
                    { @JoinColumn(name = "carpool_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "user_id", referencedColumnName = "id") })
    private Set<User> passengers=new HashSet<>();



    //GETTER AND SETTER


    public Integer getMaxPassangers() {
        return maxPassangers;
    }

    public void setMaxPassangers(Integer maxPassangers) {
        this.maxPassangers = maxPassangers;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Set<User> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<User> passengers) {
        this.passengers = passengers;
    }

    public void setCarpoolID(Integer carpoolID) {
        this.carpoolID = carpoolID;
    }


    public Integer getCarpoolID() {
        return carpoolID;
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

    public int bookIn(User user) {

            passengers.add(user);
           return 1;



    }
}

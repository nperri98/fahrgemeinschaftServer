package com.nperri.fahrgemeinschaftServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {


    @Id
    @Column(name = "id")
    private String username;
    private String surname;
    private String firstName;
    private String password;
    private String adress;
    private String city;
    private Long postalcode;
    private Date birthday;
    private Boolean teacher;




@JsonIgnore
@ManyToMany(mappedBy = "passengers",cascade = CascadeType.ALL)
private Set<Carpool> carpools=new HashSet<>();




    //GETTER AND SETTER



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(Long postalcode) {
        this.postalcode = postalcode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getTeacher() {
        return teacher;
    }

    public void setTeacher(Boolean teacher) {
        this.teacher = teacher;
    }

    public int bookIn(Carpool carpool) {
        if(carpool.getPassengers().size()<carpool.getMaxPassangers()){
            carpools.add(carpool);
            return 1;
        }else{
            return 0;
        }
    }
}

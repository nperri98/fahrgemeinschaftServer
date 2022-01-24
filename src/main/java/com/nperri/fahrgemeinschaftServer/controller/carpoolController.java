package com.nperri.fahrgemeinschaftServer.controller;

import com.nperri.fahrgemeinschaftServer.entity.Carpool;
import com.nperri.fahrgemeinschaftServer.entity.User;
import com.nperri.fahrgemeinschaftServer.repository.CarpoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carpool")
public class carpoolController {

    @Autowired
    CarpoolRepository carpoolRepository;


    @GetMapping("")
    public List<Carpool> getAllCarpools(){
        return (List<Carpool>) carpoolRepository.findAll();
    }
    @PostMapping("")
    public void createCarpool(@RequestBody Carpool carpool){
        carpoolRepository.save(carpool);
    }
    @GetMapping("/driver/{driverUsername}")
    public List<Carpool> getCarpoolByDriver(@PathVariable String driverUsername){
        List<Carpool> resultList= new ArrayList<>();

        List<Carpool> bufferList= (List<Carpool>) carpoolRepository.findAll();

        for (Carpool buffercarpool : bufferList){
            if(driverUsername.equals(buffercarpool.getDriver())){
                resultList.add(buffercarpool);
            }
        }
        if(resultList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No carpool with this driver was found");
        }
        return resultList;
    }

    @GetMapping("/passenger/{username}")
    public List<Carpool> getCarpoolsByPassanger(@PathVariable String passangerUsername){
      return null;
    }
    @PutMapping("/{CarpoolID}")
    public void updateCarpool(@PathVariable Integer carpoolID,@RequestBody Carpool carpoolUpdate){
        Optional<Carpool> carpool=carpoolRepository.findById(carpoolID);
        if (carpool.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No carpool with this id was found");
        }

        Carpool carpoolInstance= carpool.get();

        carpoolInstance.setDeparturePointCity(carpoolUpdate.getDeparturePointCity());
        carpoolInstance.setDeparturePointCityCode(carpoolUpdate.getDeparturePointCityCode());
        carpoolInstance.setDriver(carpoolUpdate.getDriver());
        carpoolInstance.setDriverEmail(carpoolUpdate.getDriverEmail());

        carpoolRepository.save(carpoolInstance);
    }
    @DeleteMapping("/{CarpoolID}")
    public void deleteCarpool(@PathVariable Integer carpoolID){
        Optional<Carpool> carpool=carpoolRepository.findById(carpoolID);

        if(carpool.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No carpool found with this ID");
        }
        carpoolRepository.deleteById(carpoolID);
    }
}

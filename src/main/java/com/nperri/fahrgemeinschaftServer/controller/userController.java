package com.nperri.fahrgemeinschaftServer.controller;
import com.nperri.fahrgemeinschaftServer.entity.User;
import com.nperri.fahrgemeinschaftServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public void createUser(@RequestBody User user){
        userRepository.save(user);
    }

    @GetMapping("/{username}")
    public User getFromUsername(@PathVariable String username){
        Optional<User> user= userRepository.findById(username);
        if(user.isPresent()) {
          return user.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this username not found");
    }

    @PutMapping("/{username}")
    public void updateUser(@PathVariable String username,@RequestBody User userUpdate){
        Optional<User> user= userRepository.findById(username);
        if(!user.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product with this username not found");

        User userInstance = user.get();

        //Update
        userInstance.setAdress(userUpdate.getAdress());
        userInstance.setBirthday(userUpdate.getBirthday());
        userInstance.setCity(userUpdate.getCity());
        userInstance.setFirstName(userUpdate.getFirstName());
        userInstance.setSurname(userUpdate.getSurname());
        userInstance.setTeacher(userUpdate.getTeacher());
        userInstance.setPostalcode(userUpdate.getPostalcode());

        userRepository.save(userInstance);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username){
        Optional<User> user= userRepository.findById(username);
        if(user.isPresent()) {
            userRepository.deleteById(username);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this username not found");
    }
}

package com.nperri.fahrgemeinschaftServer.controller;
import com.nperri.fahrgemeinschaftServer.entity.User;
import com.nperri.fahrgemeinschaftServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("")
    public User createUser(@RequestBody User user) throws ResponseStatusException {
        Optional<User> bufferUser= userRepository.findById(user.getUsername());
        if(bufferUser.isEmpty()) {

            userRepository.save(user);
            return user;
        }else {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User already exists");
        }
    }

    @GetMapping("")
    public List<User> getUsers(){
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User bodyUser ) throws ResponseStatusException {
        String username = bodyUser.getUsername();
      Optional<User> bufferUser = userRepository.findById(username);

      if(bufferUser.isEmpty()){
          throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with this username was not found");
      }
      User user = bufferUser.get();
      String bufferPass=bodyUser.getPassword();
      if(!bufferPass.equals(user.getPassword()))
          throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT,"Password does not match");

      return user;
    }

    @GetMapping("/{username}")
    public User getFromUsername(@PathVariable String username) throws ResponseStatusException {
        Optional<User> user= userRepository.findById(username);
        if(user.isPresent()) {
          return user.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this username not found");
    }

    @PutMapping("/{username}")
    public void updateUser(@PathVariable String username,@RequestBody User userUpdate) throws ResponseStatusException {
        Optional<User> user= userRepository.findById(username);
        if(user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product with this username not found");
        }

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
    public void deleteUser(@PathVariable String username) throws ResponseStatusException {
        Optional<User> user= userRepository.findById(username);
        if(user.isPresent()) {
            userRepository.deleteById(username);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this username not found");
    }
}

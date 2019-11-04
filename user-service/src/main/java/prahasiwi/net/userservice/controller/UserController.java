package prahasiwi.net.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prahasiwi.net.userservice.exception.UserNotFoundException;
import prahasiwi.net.userservice.model.User;
import prahasiwi.net.userservice.repository.UserRepository;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = {"greetings", "hello"}, method = RequestMethod.GET)
    public String greetings(){
        return "Hello World";
    }

    //Add User
    @RequestMapping(path = "user", method = RequestMethod.POST)
    public ResponseEntity<Void> addUser(@RequestBody User user){
        try{
            User createdUser = userRepository.save(user);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("id", createdUser.getUserId().toString());
            return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    //Get All User
    @RequestMapping(path = "getAllUser", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    //Get SIngle User
    @RequestMapping(path = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getSingleUser(@PathVariable("id") String id) throws UserNotFoundException {
        try {
            Optional<User> user = userRepository.findById(Long.valueOf(id));
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        }catch (Exception e){
            throw new UserNotFoundException("User not found for id: "+id);
        }
    }

    //Update User
    @RequestMapping(path = "user", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody User user){
        try{
            User updatedUser = userRepository.save(user);
            return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    //Delete User
    @RequestMapping(path = "user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deletedUser(@PathVariable("id") String id){
        try {
            userRepository.deleteById(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}

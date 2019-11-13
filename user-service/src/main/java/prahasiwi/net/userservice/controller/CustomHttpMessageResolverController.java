package prahasiwi.net.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import prahasiwi.net.userservice.exception.UserNotFoundException;
import prahasiwi.net.userservice.model.User;
import prahasiwi.net.userservice.repository.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping("userconverter")
public class CustomHttpMessageResolverController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "user", method = RequestMethod.POST, consumes = "text/user")
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

    //Get SIngle User
    @RequestMapping(path = "user/{id}", method = RequestMethod.GET, produces = "text/user")
    public ResponseEntity<User> getSingleUser(@PathVariable("id") String id) throws UserNotFoundException {
        try {
            Optional<User> user = userRepository.findById(Long.valueOf(id));
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        }catch (Exception e){
            throw new UserNotFoundException("User not found for id: "+id);
        }
    }
}

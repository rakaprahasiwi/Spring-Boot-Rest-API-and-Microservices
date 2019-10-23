package prahasiwi.net.userservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(path = {"greetings", "hello"}, method = RequestMethod.GET)
    public String greetings(){
        return "Hello World";
    }
}

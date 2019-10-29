package prahasiwi.net.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import prahasiwi.net.userservice.model.User;
import prahasiwi.net.userservice.repository.UserRepository;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("v2")
public class HateoasController {
    @Autowired
    private UserRepository userRepository;

    //Get SIngle User
    @RequestMapping(path = "user/{id}", method = RequestMethod.GET)
    public EntityModel<User> getSingleUser(@PathVariable("id") String id) throws UserPrincipalNotFoundException {
        try {
            Optional<User> user = userRepository.findById(Long.valueOf(id));
            Link selfLink = ControllerLinkBuilder
                    .linkTo(HateoasController.class)
                    .slash(user.get().getUserId())
                    .withSelfRel();

            Link deleteLink = ControllerLinkBuilder
                    .linkTo(ControllerLinkBuilder
                    .methodOn(UserController.class)
                    .deletedUser(user.get().getUserId()+""))
                    .withRel("delete");

            return new EntityModel<User>(user.get(), selfLink, deleteLink);
        }
        catch (Exception e){
            throw new UserPrincipalNotFoundException("User not found for id:"+ id);
        }
    }
}

package com.realtimeStockPortfolio.userService.controller;

import com.realtimeStockPortfolio.userService.exception.UserNotFoundException;
import com.realtimeStockPortfolio.userService.model.User;
import com.realtimeStockPortfolio.userService.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{username}")
    EntityModel<User> getUser(@PathVariable String username) {
        Optional<User> user = userService.getUser(username);

        if (!user.isPresent()) {
            throw new UserNotFoundException("User with " + username + " not found.");
        }

        EntityModel entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUser());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @PostMapping("/register")
    ResponseEntity<User> registerUser(@RequestBody @Valid User user) {
        User registeredUser = userService.registerUser(user);

        URI userLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(registeredUser.getId())
                .toUri();

        return ResponseEntity.created(userLocation).build();
    }

}

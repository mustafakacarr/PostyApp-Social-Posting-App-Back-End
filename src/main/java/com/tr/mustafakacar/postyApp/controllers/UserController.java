package com.tr.mustafakacar.postyApp.controllers;

import com.tr.mustafakacar.postyApp.entities.User;
import com.tr.mustafakacar.postyApp.exceptions.UserNotFoundException;
import com.tr.mustafakacar.postyApp.responses.UserResponse;
import com.tr.mustafakacar.postyApp.services.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1.0/users")
@OpenAPIDefinition(info = @Info(title = "PostyApp API", version = "v1.0"))
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable long userId) {
        User user = userService.getUserById(userId);
        if(user == null) {
            throw new UserNotFoundException();
        }

        return new UserResponse(user);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable long userId, @RequestBody User toUpdateUser) {
        return userService.updateUser(userId, toUpdateUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/activity/{userId}")
    public List<Object> getActivities(@PathVariable long userId) {
        return userService.getActivities(userId);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handleUserNotFound() {

    }
}

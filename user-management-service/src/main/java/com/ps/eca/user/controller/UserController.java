package com.ps.eca.user.controller;

import com.ps.eca.user.dto.RegistrationRequest;
import com.ps.eca.user.dto.UpdateUserRequest;
import com.ps.eca.user.dto.UserDto;
import com.ps.eca.user.exception.UserCreationException;
import com.ps.eca.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest request) {
        try {
            userService.registerUser(request);
            return ResponseEntity.ok("Registration successful.");
        } catch (UserCreationException userCreationException) {
            return ResponseEntity.badRequest().body(userCreationException.getMessage());
        } finally {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * TODO :-  need to implement
     */
    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUser(@PathVariable String email) {
        return ResponseEntity.ok().build();
    }

    /**
     * TODO :-  need to implement
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    /**
     * TODO :-  need to implement
     */
    @PutMapping("/{email}")
    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.noContent().build();
    }

    /**
     * TODO :-  need to implement
     */
    @DeleteMapping("/{email}")
    public ResponseEntity deleteUser(@PathVariable String email) {
        return ResponseEntity.noContent().build();
    }
}


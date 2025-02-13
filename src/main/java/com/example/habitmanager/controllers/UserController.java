package com.example.habitmanager.controllers;

import com.example.habitmanager.dto.UserDTO;
import com.example.habitmanager.dtoCreate.UserDTOCreate;
import com.example.habitmanager.serviceImp.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserServiceImp userServiceImp;

    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTOCreate> createUser(@RequestBody UserDTOCreate userDTOCreate){
        UserDTOCreate createdUser = userServiceImp.createUser(userDTOCreate);
        URI location = URI.create("/user/" + createdUser.getUser_id());
        return ResponseEntity.created(location).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id){
        UserDTO userDTO = userServiceImp.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO){
        UserDTO updatedUser = userServiceImp.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        userServiceImp.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestParam String email, @RequestParam String password){
        boolean isAuthorized = userServiceImp.userLogin(email, password);

        if(isAuthorized){
            return ResponseEntity.ok("User logged in");
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
package com.example.habitmanager.controllers;

import com.example.habitmanager.dto.UserDTO;
import com.example.habitmanager.dto.UserLoginRequestDTO;
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
        URI location = URI.create("/user/" + createdUser.getId());
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
    public ResponseEntity<UserDTOCreate> userLogin(@RequestBody UserLoginRequestDTO loginRequestDTO){
        UserDTOCreate userDTOCreate = userServiceImp.userLogin(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        if(userDTOCreate != null) {
            return ResponseEntity.ok(userDTOCreate);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
}
package com.example.habitmanager.services;

import com.example.habitmanager.dto.UserDTO;
import com.example.habitmanager.dtoCreate.UserDTOCreate;

public interface UserService {
    UserDTOCreate createUser(UserDTOCreate userDTO);
    UserDTO getUserById(int user_id);
    UserDTO updateUser(int user_id, UserDTO userDTO);
    void deleteUser(int user_id);
    boolean userLogin(String email, String password);
}
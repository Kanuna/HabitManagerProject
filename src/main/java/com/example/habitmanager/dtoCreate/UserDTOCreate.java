package com.example.habitmanager.dtoCreate;

import com.example.habitmanager.dto.UserDTO;
import com.example.habitmanager.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTOCreate extends UserDTO {
    private int user_id;
}
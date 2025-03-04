package com.example.habitmanager.serviceImp;

import com.example.habitmanager.ResourceNotFoundException.ResourceNotFoundException;
import com.example.habitmanager.dto.UserDTO;
import com.example.habitmanager.dtoCreate.UserDTOCreate;
import com.example.habitmanager.mapper.ModelMapper;
import com.example.habitmanager.models.User;
import com.example.habitmanager.repositories.UserRepository;
import com.example.habitmanager.services.UserService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImp(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTOCreate createUser(UserDTOCreate userDTOCreate) {
        User user = modelMapper.toUser(userDTOCreate);
        String userPassword = user.getPassword();

        Argon2 argon2 = Argon2Factory.create();

        String hashedPassword = argon2.hash(2, 65536, 2, userPassword.toCharArray());
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);
        return modelMapper.toUserDTOCreate(savedUser);
    }

    @Override
    public UserDTO getUserById(int user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + user_id));
        return modelMapper.toUserDTO(user);
    }

    @Override
    public UserDTO updateUser(int user_id, UserDTO userDTO) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));

        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        User updatedUser = userRepository.save(user);
        return modelMapper.toUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(int user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));
        userRepository.delete(user);
    }

    @Override
    public boolean userLogin(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        Argon2 argon2 = Argon2Factory.create();
        String userHashedPassword = user.getPassword();

        return argon2.verify(userHashedPassword, password.toCharArray());
    }
}
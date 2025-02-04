package com.example.habitmanager.serviceImp;

import com.example.habitmanager.ResourceNotFoundException.ResourceNotFoundException;
import com.example.habitmanager.dto.UserDTO;
import com.example.habitmanager.dtoCreate.UserDTOCreate;
import com.example.habitmanager.mapper.ModelMapper;
import com.example.habitmanager.models.User;
import com.example.habitmanager.repositories.UserRepository;
import com.example.habitmanager.services.UserService;
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
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + user_id));

        user.setFirst_name(userDTO.getFirst_name());
        user.setLast_name(userDTO.getLast_name());
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
        return userRepository.findByEmail(email)
                .map(user -> user.getPassword().equals(password)) // Compare passwords
                .orElse(false);
    }
}
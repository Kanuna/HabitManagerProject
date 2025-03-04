package com.example.habitmanager.serviceImp;

import com.example.habitmanager.ResourceNotFoundException.ResourceNotFoundException;
import com.example.habitmanager.dto.UserDTO;
import com.example.habitmanager.dtoCreate.UserDTOCreate;
import com.example.habitmanager.mapper.ModelMapperOld;
import com.example.habitmanager.models.User;
import com.example.habitmanager.repositories.UserRepository;
import com.example.habitmanager.services.UserService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final ModelMapperOld modelMapperOld;

    public UserServiceImp(UserRepository userRepository, ModelMapperOld modelMapperOld) {
        this.userRepository = userRepository;
        this.modelMapperOld = modelMapperOld;
    }

    @Override
    public UserDTOCreate createUser(UserDTOCreate userDTOCreate) {
        User user = new User(); //modelMapper.toUser(userDTOCreate);
        user.setFirstname(userDTOCreate.getFirstname());
        user.setLastname(userDTOCreate.getLastname());
        user.setEmail(userDTOCreate.getEmail());
        user.setAge(userDTOCreate.getAge());
        user.setPassword(userDTOCreate.getPassword());
        user.setRole(userDTOCreate.getRole());
        user.setId(userDTOCreate.getId());

        String userPassword = user.getPassword();

        Argon2 argon2 = Argon2Factory.create();

        String hashedPassword = argon2.hash(2, 65536, 2, userPassword.toCharArray());
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);
        return modelMapperOld.toUserDTOCreate(savedUser);
    }

    @Override
    public UserDTO getUserById(int user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + user_id));
        return modelMapperOld.toUserDTO(user);
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
        return modelMapperOld.toUserDTO(updatedUser);
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

        boolean isMatch = argon2.verify(userHashedPassword, password.toCharArray());

        return isMatch;
/*        return userRepository.findByEmail(email)
                .map(user -> user.getPassword().equals(password)) // Compare passwords
                .orElse(false);*/
    }
}
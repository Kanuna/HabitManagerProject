package com.example.habitmanager.services;

import com.example.habitmanager.ResourceNotFoundException.ResourceNotFoundException;
import com.example.habitmanager.dto.UserDTO;
import com.example.habitmanager.dtoCreate.UserDTOCreate;
import com.example.habitmanager.mapper.ModelMapper;
import com.example.habitmanager.models.Category;
import com.example.habitmanager.models.Habit;
import com.example.habitmanager.models.Stats;
import com.example.habitmanager.models.User;
import com.example.habitmanager.repositories.CategoryRepository;
import com.example.habitmanager.repositories.HabitRepository;
import com.example.habitmanager.repositories.StatsRepository;
import com.example.habitmanager.repositories.UserRepository;
import com.example.habitmanager.serviceImp.CategoryServiceImp;
import com.example.habitmanager.serviceImp.HabitServiceImp;
import com.example.habitmanager.serviceImp.StatsServiceImp;
import com.example.habitmanager.serviceImp.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import java.util.Optional;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private HabitRepository habitRepository;
    @Mock
    private StatsRepository statsRepository;
    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImp userServiceImp;

    private User testUser;
    private UserDTO testUserDTO;
    private UserDTOCreate testUserDTOCreate;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUser_id(1);
        testUser.setFirstname("test");
        testUser.setLastname("test");
        testUser.setAge(1);
        testUser.setEmail("test@test.com");
        testUser.setPassword("test1234");
        testUser.setRole(User.RoleEnum.USER);

        testUserDTO = new UserDTO();
        testUserDTO.setFirstname("test");
        testUserDTO.setLastname("test");
        testUserDTO.setAge(1);
        testUserDTO.setEmail("test@test.com");
        testUserDTO.setPassword("test1234");
        testUserDTO.setRole(User.RoleEnum.USER);

        testUserDTOCreate = new UserDTOCreate();
        testUserDTOCreate.setFirstname("test");
        testUserDTOCreate.setLastname("test");
        testUserDTOCreate.setAge(1);
        testUserDTOCreate.setEmail("test@test.com");
        testUserDTOCreate.setPassword("test1234");
        testUserDTOCreate.setRole(User.RoleEnum.USER);
    }


    @Test
    void createUser_WithValidUser_shouldReturnUserDTOCreate() {
        when(modelMapper.toUser(testUserDTOCreate)).thenReturn(testUser);
        when(userRepository.save(testUser)).thenReturn(testUser);
        when(modelMapper.toUserDTOCreate(testUser)).thenReturn(testUserDTOCreate);

        UserDTOCreate createdUser = userServiceImp.createUser(testUserDTOCreate);

        Assertions.assertNotNull(createdUser);
        Assertions.assertEquals(testUserDTOCreate.getUser_id(), createdUser.getUser_id());
        Assertions.assertEquals(testUserDTOCreate.getFirstname(), createdUser.getFirstname());
        Assertions.assertEquals(testUserDTOCreate.getLastname(), createdUser.getLastname());
        Assertions.assertEquals(testUserDTOCreate.getEmail(), createdUser.getEmail());
        Assertions.assertEquals(testUserDTOCreate.getPassword(), createdUser.getPassword());
        Assertions.assertEquals(testUserDTOCreate.getRole(), createdUser.getRole());
    }

    @Test
    void getUser_WithValidId_shouldReturnUserDTOById() {
        UserDTO expectedDTO = new UserDTO();
        expectedDTO.setFirstname("test");
        expectedDTO.setLastname("test");
        expectedDTO.setAge(1);
        expectedDTO.setEmail("test@test.com");
        expectedDTO.setPassword("test123");
        expectedDTO.setRole(User.RoleEnum.USER);

        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));
        when(modelMapper.toUserDTO(testUser)).thenReturn(expectedDTO);

        UserDTO result = userServiceImp.getUserById(1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedDTO.getFirstname(), result.getFirstname());
        Assertions.assertEquals(expectedDTO.getLastname(), result.getLastname());
        Assertions.assertEquals(expectedDTO.getAge(), result.getAge());
        Assertions.assertEquals(expectedDTO.getEmail(), result.getEmail());
        Assertions.assertEquals(expectedDTO.getPassword(), result.getPassword());
        Assertions.assertEquals(expectedDTO.getRole(), result.getRole());

        verify(userRepository, times(1)).findById(1);
    }
    @Test
    void getUser_WithInvalidId_shouldThrowException() {
        when(userRepository.findById(99)).thenThrow(new ResourceNotFoundException("User with id 99 not found"));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () ->  { userServiceImp.getUserById(99);
        });
    }

    @Test
    void updateUser_WithValidUser_shouldUpdateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstname("newtest");
        userDTO.setLastname("newtest");
        userDTO.setAge(1);
        userDTO.setEmail("newtest@test.com");
        userDTO.setPassword("newtest1234");
        userDTO.setRole(User.RoleEnum.USER);

        User user = new User();
        user.setFirstname("test");
        user.setLastname("test");
        user.setAge(1);
        user.setEmail("test@test.com");
        user.setPassword("test1234");
        user.setRole(User.RoleEnum.USER);

        User updatedUser = new User();
        updatedUser.setFirstname(userDTO.getFirstname());
        updatedUser.setLastname(userDTO.getLastname());
        updatedUser.setAge(userDTO.getAge());
        updatedUser.setEmail(userDTO.getEmail());
        updatedUser.setPassword(userDTO.getPassword());
        updatedUser.setRole(User.RoleEnum.USER);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(modelMapper.toUserDTO(updatedUser)).thenReturn(userDTO);

        UserDTO result = userServiceImp.updateUser(1, userDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(userDTO.getFirstname(), result.getFirstname());
        Assertions.assertEquals(userDTO.getLastname(), result.getLastname());
        Assertions.assertEquals(userDTO.getAge(), result.getAge());
        Assertions.assertEquals(userDTO.getEmail(), result.getEmail());
        Assertions.assertEquals(userDTO.getPassword(), result.getPassword());
        Assertions.assertEquals(userDTO.getRole(), result.getRole());

        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).save(testUser);
    }
    @Test
    void updateUser_WithInvalidId_shouldThrowException() {
        when(userRepository.findById(99)).thenThrow(new ResourceNotFoundException("User with id 99 not found"));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> {userServiceImp.updateUser(99, new UserDTO());
        });
    }

    @Test
    void DeleteUser_WithValidId_shouldDeleteUserAndRelatedEntities() {
        int user_id = 1;
        User user = new User();
        user.setUser_id(user_id);
        user.setFirstname("test");
        user.setLastname("test");
        user.setAge(1);
        user.setEmail("test@test.com");
        user.setPassword("test1234");
        user.setRole(User.RoleEnum.USER);

        Stats stats = new Stats();

        Habit habit = new Habit();
        habit.setHabit_id(1);
        habit.setStats(stats);
        habit.setUser(user);


        Category category = new Category();
        category.setCategory_id(1);
        category.setUser(user);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        userServiceImp.deleteUser(user_id);

        verify(userRepository, times(1)).delete(user);
        verify(userRepository, times(1)).findById(user_id);

        when(userRepository.findById(1)).thenReturn(Optional.empty());

        Optional<User> deletedUser = userRepository.findById(user_id);
        Assertions.assertTrue(deletedUser.isEmpty());
    }
}
package com.example.habitmanager.services;

import com.example.habitmanager.ResourceNotFoundException.ResourceNotFoundException;
import com.example.habitmanager.dto.HabitDTO;
import com.example.habitmanager.dtoCreate.HabitDTOCreate;
import com.example.habitmanager.models.Category;
import com.example.habitmanager.models.Habit;
import com.example.habitmanager.models.Stats;
import com.example.habitmanager.models.User;
import com.example.habitmanager.repositories.CategoryRepository;
import com.example.habitmanager.repositories.HabitRepository;
import com.example.habitmanager.repositories.StatsRepository;
import com.example.habitmanager.mapper.ModelMapper;
import com.example.habitmanager.serviceImp.HabitServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.*;


@ExtendWith(MockitoExtension.class)
public class HabitServiceTest {

    /*@Mock
    private HabitRepository habitRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private StatsRepository statsRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private HabitServiceImp habitServiceImp;

    private Habit testHabit;
    private HabitDTO testHabitDTO;
    private HabitDTOCreate testHabitDTOCreate;

    private Stats testStats;
    private User testUser;
    private Category testCategory;

    @BeforeEach
    void setUp() {
        testStats = new Stats();
        testStats.setStats_id(1);
        testStats.setFinishedTotalTimesWeek(0);
        testStats.setFinishedTotalTimesMonth(0);
        testStats.setNotFinishedTotalTimesWeek(0);
        testStats.setNotFinishedTotalTimesMonth(0);
        testStats.setNotFinishedTotalTimesYear(0);

        testUser = new User();
        testUser.setUser_id(1);
        testUser.setFirstname("Test");
        testUser.setLastname("User");
        testUser.setAge(1);
        testUser.setEmail("test@test.com");
        testUser.setPassword("password");
        testUser.setRole(User.RoleEnum.USER);
        testUser.setCategories(Collections.singletonList(testCategory));

        testCategory = new Category();
        testCategory.setCategory_id(1);
        testCategory.setName("test category");
        testCategory.setColorCode("#787276");
        testCategory.setUser(testUser);


        testHabit = new Habit();
        testHabit.setHabit_id(1);
        testHabit.setTitle("Test habit");
        testHabit.setDescription("Test habit description");
        testHabit.setAmountAWeek(0);
        testHabit.setHabitType(Habit.HabitType.WEEKLY);
        testHabit.setCategory(testCategory);
        testHabit.setStats(testStats);
        testHabit.setUser(testUser);

        testHabitDTO = new HabitDTO();
        testHabitDTO.setTitle("Test habit");
        testHabitDTO.setDescription("Test habit description");
        testHabitDTO.setAmountAWeek(0);
        testHabitDTO.setHabitType(Habit.HabitType.WEEKLY);
        testHabitDTO.setCategory(testCategory);
        testHabitDTO.setStats(testStats);
        testHabitDTO.setUser(testUser);

        testHabitDTOCreate = new HabitDTOCreate();
        testHabitDTOCreate.setTitle("Test habit");
        testHabitDTOCreate.setDescription("Test habit description");
        testHabitDTOCreate.setAmountAWeek(0);
        testHabitDTOCreate.setHabitType(Habit.HabitType.WEEKLY);
        testHabitDTOCreate.setCategory(testCategory);
        testHabitDTOCreate.setStats(testStats);
        testHabitDTOCreate.setUser(testUser);
    }

    @Test
    void createHabit_withTypeWeekly() {
        testHabitDTOCreate.setHabitType(Habit.HabitType.WEEKLY);
        testHabitDTOCreate.setAmountAWeek(4);

        when(modelMapper.toHabit(testHabitDTOCreate)).thenReturn(testHabit);
        when(habitRepository.save(testHabit)).thenReturn(testHabit);
        when(modelMapper.toHabitDTOCreate(testHabit)).thenReturn(testHabitDTOCreate);

        HabitDTOCreate createdHabit = habitServiceImp.createHabit(1, testHabitDTOCreate);

        Assertions.assertNotNull(createdHabit);
        Assertions.assertEquals(Habit.HabitType.WEEKLY, createdHabit.getHabitType());
        Assertions.assertEquals(4, createdHabit.getAmountAWeek());

        verify(habitRepository, times(1)).save(testHabit);
    }
    @Test
    void createHabit_withTypeSpecificDays() {
        testHabitDTOCreate.setHabitType(Habit.HabitType.SPECIFIC_DAYS);
        testHabitDTOCreate.setSpecificDays(Arrays.asList(Habit.daysEnum.MONDAY, Habit.daysEnum.WEDNESDAY, Habit.daysEnum.FRIDAY));

        when(modelMapper.toHabit(testHabitDTOCreate)).thenReturn(testHabit);
        when(habitRepository.save(testHabit)).thenReturn(testHabit);
        when(modelMapper.toHabitDTOCreate(testHabit)).thenReturn(testHabitDTOCreate);

        HabitDTOCreate createdHabit = habitServiceImp.createHabit(1, testHabitDTOCreate);

        Assertions.assertNotNull(createdHabit);
        Assertions.assertEquals(Habit.HabitType.SPECIFIC_DAYS, createdHabit.getHabitType());
        Assertions.assertTrue(createdHabit.getSpecificDays().contains(Habit.daysEnum.MONDAY));
        Assertions.assertTrue(createdHabit.getSpecificDays().contains(Habit.daysEnum.WEDNESDAY));
        Assertions.assertTrue(createdHabit.getSpecificDays().contains(Habit.daysEnum.FRIDAY));

        verify(habitRepository, times(1)).save(testHabit);
    }
    @Test
    void createHabit_withTypeSpecificDates() {
        testHabitDTOCreate.setHabitType(Habit.HabitType.SPECIFIC_DATES);
        testHabitDTOCreate.setSpecificDates(Arrays.asList(LocalDate.of(2025, 2, 20),
                                                          LocalDate.of(2025, 2, 25)));

        when(modelMapper.toHabit(testHabitDTOCreate)).thenReturn(testHabit);
        when(habitRepository.save(testHabit)).thenReturn(testHabit);
        when(modelMapper.toHabitDTOCreate(testHabit)).thenReturn(testHabitDTOCreate);

        HabitDTOCreate createdHabit = habitServiceImp.createHabit(1, testHabitDTOCreate);

        Assertions.assertNotNull(createdHabit);
        Assertions.assertEquals(Habit.HabitType.SPECIFIC_DATES, createdHabit.getHabitType());
        Assertions.assertTrue(createdHabit.getSpecificDates().contains(LocalDate.of(2025, 2, 20)));
        Assertions.assertTrue(createdHabit.getSpecificDates().contains(LocalDate.of(2025, 2, 25)));

        verify(habitRepository, times(1)).save(testHabit);
    }

    @Test
    void getHabit_WithValidId() {
        Stats expectedStats = new Stats();
        expectedStats.setStats_id(1);
        expectedStats.setFinishedTotalTimesWeek(0);
        expectedStats.setFinishedTotalTimesMonth(0);
        expectedStats.setFinishedTotalTimesYear(0);
        expectedStats.setNotFinishedTotalTimesWeek(0);
        expectedStats.setNotFinishedTotalTimesMonth(0);
        expectedStats.setNotFinishedTotalTimesYear(0);

        Category expectedCategory = new Category();
        expectedCategory.setCategory_id(1);
        expectedCategory.setName("test category");
        expectedCategory.setColorCode("#787276");

        User expectedUser = new User();
        expectedUser.setUser_id(1);
        expectedUser.setFirstname("Test");
        expectedUser.setLastname("Test");
        expectedUser.setAge(1);
        expectedUser.setEmail("test@test.com");
        expectedUser.setPassword("password");
        expectedUser.setRole(User.RoleEnum.USER);
        expectedUser.setCategories(Collections.singletonList(expectedCategory));

        HabitDTO expectedDTO = new HabitDTO();
        expectedDTO.setTitle("Test habit");
        expectedDTO.setDescription("Test habit description");
        expectedDTO.setStats(expectedStats); //Could use testStats instead
        expectedDTO.setUser(expectedUser); //Could use testUser instead
        expectedDTO.setCategory(expectedCategory); //Could use testCategory instead
        expectedDTO.setAmountAWeek(0);
        expectedDTO.setHabitType(Habit.HabitType.WEEKLY);


        when(habitRepository.findById(1)).thenReturn(Optional.of(testHabit));
        when(modelMapper.toHabitDTO(testHabit)).thenReturn(expectedDTO);

        HabitDTO result = habitServiceImp.getHabitById(1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedDTO.getHabitType(), result.getHabitType());
        Assertions.assertEquals(expectedDTO.getTitle(), result.getTitle());
        Assertions.assertEquals(expectedDTO.getDescription(), result.getDescription());
        Assertions.assertEquals(expectedDTO.getAmountAWeek(), result.getAmountAWeek());
        Assertions.assertEquals(expectedDTO.getStats(), result.getStats());
        Assertions.assertEquals(expectedDTO.getUser(), result.getUser());
        Assertions.assertEquals(expectedDTO.getCategory(), result.getCategory());
        Assertions.assertEquals(expectedDTO.getSpecificDays(), result.getSpecificDays());
        Assertions.assertEquals(expectedDTO.getSpecificDates(), result.getSpecificDates());

        verify(habitRepository, times(1)).findById(1);
    }
    @Test
    void getUser_WithInvalidId_shouldThrowException() {
        when(habitRepository.findById(99)).thenThrow(new ResourceNotFoundException("Habit with id 99 not found"));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () ->  { habitServiceImp.getHabitById(99);
                });
    }

    @Test
    void updateHabit_WithValidId()
    {
        Stats newStats = new Stats();
        newStats.setStats_id(1);
        newStats.setFinishedTotalTimesWeek(1);
        newStats.setFinishedTotalTimesMonth(1);
        newStats.setNotFinishedTotalTimesWeek(2);
        newStats.setNotFinishedTotalTimesWeek(1);
        newStats.setNotFinishedTotalTimesMonth(1);
        newStats.setNotFinishedTotalTimesYear(2);

        Category newCategory = new Category();
        newCategory.setCategory_id(1);
        newCategory.setName("new test category");
        newCategory.setColorCode("#787878");

        User newUser = new User();
        newUser.setUser_id(1);
        newUser.setFirstname("Test");
        newUser.setLastname("Test");
        newUser.setAge(1);
        newUser.setEmail("test@test.com");
        newUser.setPassword("password");
        newUser.setRole(User.RoleEnum.USER);
        newUser.setCategories(Collections.singletonList(newCategory));

        HabitDTO habitDTO = new HabitDTO();
        habitDTO.setTitle("New test habit");
        habitDTO.setDescription("New test habit description");
        habitDTO.setHabitType(Habit.HabitType.SPECIFIC_DAYS);
        habitDTO.setAmountAWeek(3);
        habitDTO.setSpecificDays(Arrays.asList(Habit.daysEnum.MONDAY, Habit.daysEnum.TUESDAY, Habit.daysEnum.WEDNESDAY));
        habitDTO.setStats(newStats);
        habitDTO.setUser(newUser);
        habitDTO.setCategory(newCategory);

        Habit habit = new Habit();
        habit.setHabit_id(1);
        habit.setTitle("Test habit");
        habit.setDescription("Test habit description");
        habit.setHabitType(Habit.HabitType.WEEKLY);
        habit.setAmountAWeek(0);
        habit.setStats(testStats);
        habit.setUser(testUser);
        habit.setCategory(testCategory);

        Habit updatedHabit = new Habit();
        updatedHabit.setTitle(habitDTO.getTitle());
        updatedHabit.setDescription(habitDTO.getDescription());
        updatedHabit.setHabitType(habitDTO.getHabitType());
        updatedHabit.setAmountAWeek(habitDTO.getAmountAWeek());
        updatedHabit.setSpecificDays(habitDTO.getSpecificDays());
        updatedHabit.setStats(habitDTO.getStats());
        updatedHabit.setUser(habitDTO.getUser());
        updatedHabit.setCategory(habitDTO.getCategory());

        when(habitRepository.findById(1)).thenReturn(Optional.of(habit));
        when(habitRepository.save(habit)).thenReturn(updatedHabit);
        when(modelMapper.toHabitDTO(updatedHabit)).thenReturn(habitDTO);

        HabitDTO result = habitServiceImp.updateHabit(1, habitDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(habitDTO.getTitle(), result.getTitle());
        Assertions.assertEquals(habitDTO.getDescription(), result.getDescription());
        Assertions.assertEquals(habitDTO.getHabitType(), result.getHabitType());
        Assertions.assertEquals(habitDTO.getAmountAWeek(), result.getAmountAWeek());
        Assertions.assertEquals(habitDTO.getSpecificDays(), result.getSpecificDays());
        Assertions.assertEquals(habitDTO.getStats(), result.getStats());
        Assertions.assertEquals(habitDTO.getUser(), result.getUser());
        Assertions.assertEquals(habitDTO.getCategory(), result.getCategory());
    }
    @Test
    void updateHabit_WithInvalidId_shouldThrowException() {
        when(habitRepository.findById(99)).thenThrow(new ResourceNotFoundException("Habit with id 99 not found"));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> { habitServiceImp.updateHabit(99, new HabitDTO());
                });
    }

    @Test
    void deleteHabit_WithValidId_shouldDeleteHabitAndStats() {
        int habit_id = 1;
        Habit habit = new Habit();
        habit.setHabit_id(habit_id);
        habit.setTitle("Test habit");
        habit.setDescription("Test habit description");
        habit.setHabitType(Habit.HabitType.WEEKLY);
        habit.setAmountAWeek(0);
        habit.setStats(testStats);


        when(habitRepository.findById(1)).thenReturn(Optional.of(habit));
        habitServiceImp.deleteHabit(habit_id);

        verify(habitRepository, times(1)).delete(habit);
        verify(habitRepository, times(1)).findById(habit_id);

        when(habitRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Habit> deletedHabit = habitRepository.findById(habit_id);
        Assertions.assertTrue(deletedHabit.isEmpty());
    }
    @Test
    void deleteHabit_WithInvalidId_shouldThrowException() {
        when(habitRepository.findById(99)).thenThrow(new ResourceNotFoundException("Habit with id 99 not found"));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () ->  { habitServiceImp.getHabitById(99);
                });
    }

    @Test
    void getAllHabits_withValidUserId(){
        int user_id = 1;
        User user = new User();
        user.setUser_id(user_id);


        Habit habit1 = new Habit();
        habit1.setUser(user);
        habit1.setTitle("Habit1");

        Habit habit2 = new Habit();
        habit2.setUser(user);
        habit2.setTitle("Habit2");

        List<Habit> habits = Arrays.asList(habit1, habit2);

        HabitDTO habitDTO1 = new HabitDTO();
        habitDTO1.setTitle("Habit1");

        HabitDTO habitDTO2 = new HabitDTO();
        habitDTO2.setTitle("Habit2");

        when(habitRepository.findByUser_UserId(user_id)).thenReturn(Optional.of(habits));
        when(modelMapper.toHabitDTO(habit1)).thenReturn(habitDTO1);
        when(modelMapper.toHabitDTO(habit2)).thenReturn(habitDTO2);

        List<HabitDTO> result = habitServiceImp.getAllHabitsFromUser(user_id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(habitDTO1.getTitle(), result.get(0).getTitle());
        Assertions.assertEquals(habitDTO2.getTitle(), result.get(1).getTitle());
    }
    @Test
    void getAllHabits_withInvalidUserId(){
        when(habitRepository.findByUser_UserId(99))
                .thenThrow(new ResourceNotFoundException("User with id 99 not found"));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> {
                    habitServiceImp.getAllHabitsFromUser(99);
                });
    }

    @Test
    void getAllHabits_withValidUserIdAndDate(){
        Habit habit1 = new Habit();
        habit1.setUser(testUser);
        habit1.setTitle("Habit1");
        habit1.setSpecificDates(Arrays.asList(
                LocalDate.of(2025, 2, 1),
                LocalDate.of(2025, 2, 2)));

        Habit habit2 = new Habit();
        habit2.setUser(testUser);
        habit2.setTitle("Habit2");
        habit2.setSpecificDates(Arrays.asList(
                LocalDate.of(2025, 2, 1),
                LocalDate.of(2025, 2, 2)));

        List<Habit> habits = Arrays.asList(habit1, habit2);

        HabitDTO habitDTO1 = new HabitDTO();
        habitDTO1.setTitle("Habit1");

        HabitDTO habitDTO2 = new HabitDTO();
        habitDTO2.setTitle("Habit2");

        when(habitRepository.findByUser_UserId(testUser.getUser_id())).thenReturn(Optional.of(habits));
        when(modelMapper.toHabitDTO(habit1)).thenReturn(habitDTO1);
        when(modelMapper.toHabitDTO(habit2)).thenReturn(habitDTO2);

        List<HabitDTO> result = habitServiceImp.getAllHabitsFromUser(testUser.getUser_id());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(habitDTO1.getTitle(), result.get(0).getTitle());
        Assertions.assertEquals(habitDTO2.getSpecificDates(), result.get(0).getSpecificDates());
        Assertions.assertEquals(habitDTO2.getTitle(), result.get(1).getTitle());
        Assertions.assertEquals(habitDTO2.getSpecificDates(), result.get(1).getSpecificDates());
    }
    @Test
    void getAllHabits_withInvalidUserIdAndValidDate_shouldThrowException(){
        LocalDate date = LocalDate.of(2025, 2, 3);
        when(habitRepository.findByUser_UserIdAndDate(99, date))
            .thenThrow(new ResourceNotFoundException("Habits with user id 99 not found"));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> {
                    habitServiceImp.getAllHabitsFromUserAndDate(99, date);
                });
    }

    @Test
    void getAllHabits_withValidUserIdAndCategory(){
        Habit habit1 = new Habit();
        habit1.setUser(testUser);
        habit1.setTitle("Habit1");
        habit1.setCategory(testCategory);

        Habit habit2 = new Habit();
        habit2.setUser(testUser);
        habit2.setTitle("Habit2");
        habit2.setCategory(testCategory);

        List<Habit> habits = Arrays.asList(habit1, habit2);

        HabitDTO habitDTO1 = new HabitDTO();
        habitDTO1.setTitle("Habit1");
        habitDTO1.setCategory(testCategory);

        HabitDTO habitDTO2 = new HabitDTO();
        habitDTO2.setTitle("Habit2");
        habitDTO2.setCategory(testCategory);

        when(habitRepository.findByUser_UserIdAndCategory_CategoryId(testUser.getUser_id(), testCategory.getCategory_id())).thenReturn(Optional.of(habits));
        when(modelMapper.toHabitDTO(habit1)).thenReturn(habitDTO1);
        when(modelMapper.toHabitDTO(habit2)).thenReturn(habitDTO2);

        List<HabitDTO> result = habitServiceImp.getAllHabitsFromUserAndCategory(testUser.getUser_id(), testCategory.getCategory_id());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(habitDTO1.getTitle(), result.get(0).getTitle());
        Assertions.assertEquals(habitDTO2.getCategory(), result.get(0).getCategory());
        Assertions.assertEquals(habitDTO2.getTitle(), result.get(1).getTitle());
        Assertions.assertEquals(habitDTO2.getCategory(), result.get(1).getCategory());
    }
    @Test
    void getAllHabits_withInvalidUserIdAndValidCategory_shouldThrowException(){
        when(habitRepository.findByUser_UserIdAndCategory_CategoryId(99, testCategory.getCategory_id()))
                .thenThrow(new ResourceNotFoundException("Habits with user id 99 not found"));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> {
                    habitServiceImp.getAllHabitsFromUserAndCategory(99, testCategory.getCategory_id());
                });
    }

    @Test
    void getAllHabits_withValidUserAndPriority(){
        Habit.Priority priority = Habit.Priority.LOW;
        Habit habit1 = new Habit();
        habit1.setUser(testUser);
        habit1.setTitle("Habit1");
        habit1.setCategory(testCategory);
        habit1.setPriority(priority);

        Habit habit2 = new Habit();
        habit2.setUser(testUser);
        habit2.setTitle("Habit2");
        habit2.setCategory(testCategory);
        habit2.setPriority(priority);

        List<Habit> habits = Arrays.asList(habit1, habit2);

        HabitDTO habitDTO1 = new HabitDTO();
        habitDTO1.setTitle("Habit1");
        habitDTO1.setCategory(testCategory);

        HabitDTO habitDTO2 = new HabitDTO();
        habitDTO2.setTitle("Habit2");
        habitDTO2.setCategory(testCategory);

        when(habitRepository.findByUser_UserIdAndPriority(testUser.getUser_id(), priority)).thenReturn(Optional.of(habits));
        when(modelMapper.toHabitDTO(habit1)).thenReturn(habitDTO1);
        when(modelMapper.toHabitDTO(habit2)).thenReturn(habitDTO2);

        List<HabitDTO> result = habitServiceImp.getAllHabitsFromUserAndPriority(testUser.getUser_id(), priority);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(habitDTO1.getTitle(), result.get(0).getTitle());
        Assertions.assertEquals(habitDTO1.getCategory(), result.get(0).getCategory());
        Assertions.assertEquals(habitDTO1.getPriority(), result.get(0).getPriority());
        Assertions.assertEquals(habitDTO2.getTitle(), result.get(1).getTitle());
        Assertions.assertEquals(habitDTO2.getCategory(), result.get(1).getCategory());
        Assertions.assertEquals(habitDTO2.getPriority(), result.get(1).getPriority());
    }
    @Test
    void getAllHabits_withInvalidUserAndValidPriority_shouldThrowException(){
        Habit.Priority priority = Habit.Priority.LOW;
        when(habitRepository.findByUser_UserIdAndPriority(99, priority))
                .thenThrow(new ResourceNotFoundException("Habits with user id 99 not found"));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> {
                    habitServiceImp.getAllHabitsFromUserAndPriority(99, priority);
                });
    }*/
}
package com.example.habitmanager.services;

import com.example.habitmanager.ResourceNotFoundException.ResourceNotFoundException;
import com.example.habitmanager.dto.CategoryDTO;
import com.example.habitmanager.dtoCreate.CategoryDTOCreate;
import com.example.habitmanager.mapper.ModelMapper;
import com.example.habitmanager.models.Category;
import com.example.habitmanager.models.Habit;
import com.example.habitmanager.models.User;
import com.example.habitmanager.repositories.CategoryRepository;
import com.example.habitmanager.repositories.UserRepository;
import com.example.habitmanager.serviceImp.CategoryServiceImp;
import com.example.habitmanager.serviceImp.UserServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CategoryServiceImp categoryServiceImp;
    @InjectMocks
    private UserServiceImp userServiceImp;

    private Category testCategory;
    private CategoryDTO testCategoryDTO;
    private CategoryDTOCreate testCategoryDTOCreate;

    private User testUser;

    private List<Habit> habits = Arrays.asList(new Habit(), new Habit());

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1);
        testUser.setFirstname("test");
        testUser.setLastname("test");
        testUser.setEmail("test@test.com");
        testUser.setAge(99);
        testUser.setRole(User.RoleEnum.USER);
        testUser.setPassword("testPass");
        testUser.setCategories(Collections.singletonList(testCategory));
        testUser.setHabits(habits);

        testCategory = new Category();
        testCategory.setId(1);
        testCategory.setUser(testUser);
        testCategory.setHabits(habits);
        testCategory.setName("test category");
        testCategory.setColorCode("#123456");

        testCategoryDTO = new CategoryDTO();
        testCategoryDTO.setUser(testUser);
        testCategoryDTO.setHabits(habits);
        testCategoryDTO.setName("test category");
        testCategoryDTO.setColorCode("#123456");

        testCategoryDTOCreate = new CategoryDTOCreate();
        testCategoryDTOCreate.setUser(testUser);
        testCategoryDTOCreate.setHabits(habits);
        testCategoryDTOCreate.setName("test category");
        testCategoryDTOCreate.setColorCode("#123456");
    }

    @Test
    void createCategory_WithValidCategory() {
        when(modelMapper.toCategory(testCategoryDTOCreate)).thenReturn(testCategory);
        when(categoryRepository.save(testCategory)).thenReturn(testCategory);
        when(modelMapper.toCategoryDTOCreate(testCategory)).thenReturn(testCategoryDTOCreate);

        CategoryDTOCreate createdCategory = categoryServiceImp.createCategory(1, testCategoryDTOCreate);

        Assertions.assertNotNull(createdCategory);
        Assertions.assertEquals(testCategoryDTOCreate.getUser(), createdCategory.getUser());
        Assertions.assertEquals(testCategoryDTOCreate.getId(), createdCategory.getId());
        Assertions.assertEquals(testCategoryDTOCreate.getColorCode(), createdCategory.getColorCode());
        Assertions.assertEquals(testCategoryDTOCreate.getHabits(), createdCategory.getHabits());
        Assertions.assertEquals(testCategoryDTOCreate.getName(), createdCategory.getName());

        verify(modelMapper, times(1)).toCategory(testCategoryDTOCreate);
        verify(categoryRepository, times(1)).save(testCategory);
        verify(modelMapper, times(1)).toCategoryDTOCreate(testCategory);
    }

    @Test
    void getCategory_WithValidId(){
        Category expectedCategory = new Category();
        expectedCategory.setId(1);
        expectedCategory.setUser(testUser);
        expectedCategory.setHabits(habits);
        expectedCategory.setName("test category");
        expectedCategory.setColorCode("#123456");

        when(categoryRepository.findById(1)).thenReturn(Optional.of(testCategory));
        when(modelMapper.toCategoryDTO(testCategory)).thenReturn(testCategoryDTO);

        CategoryDTO result = categoryServiceImp.getCategoryFromId(1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(testCategoryDTO.getName(), result.getName());
        Assertions.assertEquals(testCategoryDTO.getUser(), result.getUser());
        Assertions.assertEquals(testCategoryDTO.getHabits(), result.getHabits());
        Assertions.assertEquals(testCategoryDTO.getColorCode(), result.getColorCode());

        verify( categoryRepository, times(1)).findById(1);
        verify( modelMapper, times(1)).toCategoryDTO(testCategory);
    }
    @Test
    void getCategory_WithInvalidId_shouldThrowException(){
        when(categoryRepository.findById(99)).thenThrow(new ResourceNotFoundException("Category not found with id: 99"));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> categoryServiceImp.getCategoryFromId(99));

        verify(categoryRepository, times(1)).findById(99);
    }

    @Test
    void updateCategory_WithValidCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("new test category");
        categoryDTO.setColorCode("#654321");
        categoryDTO.setHabits(Arrays.asList(new Habit(), new Habit(), new Habit()));
        categoryDTO.setUser(new User());

        Category category = new Category();
        category.setId(1);
        category.setUser(testUser);
        category.setHabits(habits);
        category.setName("test category");
        category.setColorCode("#123456");

        Category updatedCategory = new Category();
        category.setUser(categoryDTO.getUser());
        category.setHabits(categoryDTO.getHabits());
        category.setName(categoryDTO.getName());
        category.setColorCode(categoryDTO.getColorCode());

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(updatedCategory);
        when(modelMapper.toCategoryDTO(updatedCategory)).thenReturn(categoryDTO);

        CategoryDTO result = categoryServiceImp.updateCategory(1, categoryDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(categoryDTO.getName(), result.getName());
        Assertions.assertEquals(categoryDTO.getColorCode(), result.getColorCode());
        Assertions.assertEquals(categoryDTO.getHabits(), result.getHabits());
        Assertions.assertEquals(categoryDTO.getUser(), result.getUser());

        verify( categoryRepository, times(1)).findById(1);
        verify( categoryRepository, times(1)).save(category);
        verify( modelMapper, times(1)).toCategoryDTO(updatedCategory);
    }
    @Test
    void updateCategory_WithInvalidId_shouldThrowException(){
        when(categoryRepository.findById(99)).thenThrow(new ResourceNotFoundException("Category with id 99 not found"));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> categoryServiceImp.updateCategory(99, new CategoryDTO()));

        verify(categoryRepository, times(1)).findById(99);
    }

    @Test
    void getCategories_WithValidUserId(){
        int user_id = testUser.getId();
        Category category1 = new Category();
        category1.setId(1);
        category1.setName("category 1");
        category1.setUser(testUser);
        category1.setColorCode("#123456");

        Category category2 = new Category();
        category2.setId(2);
        category2.setName("category 2");
        category2.setUser(testUser);
        category2.setColorCode("#123456");

        List<Category> categories = Arrays.asList(category1, category2);

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setName("category 1");
        categoryDTO1.setColorCode("#123456");
        categoryDTO1.setUser(testUser);

        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setName("category 2");
        categoryDTO2.setColorCode("#123456");
        category2.setUser(testUser);

        when(categoryRepository.findByUser_id(user_id)).thenReturn(Optional.of(categories));
        when(modelMapper.toCategoryDTO(category1)).thenReturn(categoryDTO1);
        when(modelMapper.toCategoryDTO(category2)).thenReturn(categoryDTO2);

        List<CategoryDTO> result = categoryServiceImp.getAllCategoriesFromUser(user_id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(categories.size(), result.size());
        Assertions.assertEquals(categoryDTO1.getName(), result.get(0).getName());
        Assertions.assertEquals(categoryDTO1.getColorCode(), result.get(0).getColorCode());
        Assertions.assertEquals(categoryDTO1.getUser(), result.get(0).getUser());

        Assertions.assertEquals(categoryDTO2.getName(), result.get(1).getName());
        Assertions.assertEquals(categoryDTO2.getColorCode(), result.get(1).getColorCode());
        Assertions.assertEquals(categoryDTO2.getUser(), result.get(1).getUser());

        verify( categoryRepository, times(1)).findByUser_id(user_id);
        verify( modelMapper, times(1)).toCategoryDTO(category1);
        verify( modelMapper, times(1)).toCategoryDTO(category2);

    }

    @Test
    void deleteCategory_WithValidId(){
        int category_id = 1;
        Category category = new Category();
    }
    @Test
    void deleteCategory_WithInvalidId_shouldThrowException(){
        when(categoryRepository.findById(99)).thenThrow(new ResourceNotFoundException("Category with id 99 not found"));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> categoryServiceImp.getCategoryFromId(99));

        verify(categoryRepository, times(1)).findById(99);
    }
}
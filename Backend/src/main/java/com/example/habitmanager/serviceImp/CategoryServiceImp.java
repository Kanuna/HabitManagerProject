package com.example.habitmanager.serviceImp;

import com.example.habitmanager.ResourceNotFoundException.ResourceNotFoundException;
import com.example.habitmanager.dto.CategoryDTO;
import com.example.habitmanager.dtoCreate.CategoryDTOCreate;
import com.example.habitmanager.mapper.ModelMapper;
import com.example.habitmanager.models.Category;
import com.example.habitmanager.models.Habit;
import com.example.habitmanager.models.User;
import com.example.habitmanager.repositories.CategoryRepository;
import com.example.habitmanager.repositories.UserRepository;
import com.example.habitmanager.services.CategoryService;
import com.example.habitmanager.repositories.HabitRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImp(CategoryRepository categoryRepository, HabitRepository habitRepository,
                              UserRepository userRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDTOCreate createCategory(int user_id, CategoryDTOCreate categoryDTOCreate) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + user_id + ". For Category creation."));
        Category category = modelMapper.toCategory(categoryDTOCreate);
        category.setUser(user);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.toCategoryDTOCreate(savedCategory);
    }

    @Override
    public CategoryDTO getCategoryFromId(int category_id) {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + category_id));
        return modelMapper.toCategoryDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(int category_id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + category_id));

        category.setName(categoryDTO.getName());
        category.setColorCode(categoryDTO.getColorCode());

        Category savedCategory = categoryRepository.save(category);
        return modelMapper.toCategoryDTO(savedCategory);
    }

    @Override
    public List<CategoryDTO> getAllCategoriesFromUser(int userId) {
        List<Category> categories = categoryRepository.findByUser_id(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return categories.stream()
                .map(modelMapper::toCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(int category_id, int user_id) {
        Optional<List<Habit>> optionalHabits = habitRepository.findByUser_idAndCategory_id(user_id, category_id);
        if(optionalHabits.isPresent()) {
            List<Habit> habits = optionalHabits.get();
            for (Habit habit : habits) {
                habit.setCategory(null);
                habitRepository.save(habit);
            }
        }

        categoryRepository.deleteById(category_id);
    }
}
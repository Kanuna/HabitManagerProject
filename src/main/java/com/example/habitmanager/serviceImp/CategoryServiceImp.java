package com.example.habitmanager.serviceImp;

import com.example.habitmanager.ResourceNotFoundException.ResourceNotFoundException;
import com.example.habitmanager.dto.CategoryDTO;
import com.example.habitmanager.dtoCreate.CategoryDTOCreate;
import com.example.habitmanager.mapper.ModelMapper;
import com.example.habitmanager.models.Category;
import com.example.habitmanager.repositories.CategoryRepository;
import com.example.habitmanager.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImp(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDTOCreate createCategory(CategoryDTOCreate categoryDTOCreate) {
        Category category = modelMapper.toCategory(categoryDTOCreate);
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
    public List<CategoryDTO> getAllCategoriesFromUser(int user_id) {
        List<Category> categories = categoryRepository.findByUserId(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));

        return categories.stream()
                .map(modelMapper::toCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(int category_id) {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + category_id));
        categoryRepository.deleteById(category_id);
    }
}

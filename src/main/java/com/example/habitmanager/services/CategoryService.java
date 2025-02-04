package com.example.habitmanager.services;

import com.example.habitmanager.dto.CategoryDTO;
import com.example.habitmanager.dtoCreate.CategoryDTOCreate;

import java.util.List;

public interface CategoryService {
    CategoryDTOCreate createCategory(CategoryDTOCreate categoryDTOCreate);
    CategoryDTO getCategoryFromId(int category_id);
    CategoryDTO updateCategory(int category_id, CategoryDTO categoryDTO);
    List<CategoryDTO> getAllCategoriesFromUser(int user_id);
    void deleteCategory(int category_id);
}

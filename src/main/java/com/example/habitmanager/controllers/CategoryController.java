package com.example.habitmanager.controllers;

import com.example.habitmanager.dto.CategoryDTO;
import com.example.habitmanager.dtoCreate.CategoryDTOCreate;
import com.example.habitmanager.serviceImp.CategoryServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    private final CategoryServiceImp categoryServiceImp;

    public CategoryController(CategoryServiceImp categoryServiceImp) {
        this.categoryServiceImp = categoryServiceImp;
    }

    @PostMapping("/{user_id}/categories")
    public ResponseEntity<CategoryDTOCreate> createCategory(@PathVariable int user_id, @RequestBody CategoryDTOCreate categoryDTOCreate) {
        CategoryDTOCreate createdCategory = categoryServiceImp.createCategory(user_id, categoryDTOCreate);

        URI location = URI.create("/category/" + createdCategory.getCategory_id());
        return ResponseEntity.created(location).body(createdCategory);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable int category_id) {
        CategoryDTO categoryDTO = categoryServiceImp.getCategoryFromId(category_id);
        return ResponseEntity.ok(categoryDTO);
    }

/*    @GetMapping("/users/{user_id}/categories")
    public ResponseEntity<List<CategoryDTO>> getCategories(@PathVariable int user_id) {
        List<CategoryDTO> categories = categoryServiceImp.getAllCategoriesFromUser(user_id);
        return ResponseEntity.ok(categories);
    }*/

    @PutMapping("/{category_id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable int category_id, @RequestBody CategoryDTO categoryDTO) {
        categoryServiceImp.updateCategory(category_id, categoryDTO);
        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("/users/{user_id}/categories/{category_id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int user_id, @PathVariable int category_id) {
        categoryServiceImp.deleteCategory(category_id, user_id);
        return ResponseEntity.noContent().build();
    }
}
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

    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDTOCreate> createCategory(@RequestBody CategoryDTOCreate categoryDTOCreate) {
        CategoryDTOCreate createdCategory = categoryServiceImp.createCategory(categoryDTOCreate);

        URI location = URI.create("/category/" + createdCategory.getCategory_id());
        return ResponseEntity.created(location).body(createdCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable int id) {
        CategoryDTO categoryDTO = categoryServiceImp.getCategoryFromId(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("/categories/{user_id}")
    public ResponseEntity<List<CategoryDTO>> getCategories(@PathVariable int user_id) {
        List<CategoryDTO> categories = categoryServiceImp.getAllCategoriesFromUser(user_id);
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable int id, @RequestBody CategoryDTO categoryDTO) {
        categoryServiceImp.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryServiceImp.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
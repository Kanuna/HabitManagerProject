package com.example.habitmanager.repositories;

import com.example.habitmanager.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

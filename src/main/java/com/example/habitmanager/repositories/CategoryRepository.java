package com.example.habitmanager.repositories;

import com.example.habitmanager.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<List<Category>> findByUser_User_id(int user_id);
}

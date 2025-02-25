package com.example.habitmanager.repositories;

import com.example.habitmanager.models.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Integer> {
    Optional<Stats> findByHabit_Id(int habit_id);
}

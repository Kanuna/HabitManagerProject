package com.example.habitmanager.repositories;

import com.example.habitmanager.models.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatsRepository extends JpaRepository<Stats, Integer> {
}

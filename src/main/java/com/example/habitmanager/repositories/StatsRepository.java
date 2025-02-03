package com.example.habitmanager.repositories;

import com.example.habitmanager.models.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Integer> {
}

package com.example.series.repository;

import com.example.series.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwardRepository  extends JpaRepository<Award, Long> {
}

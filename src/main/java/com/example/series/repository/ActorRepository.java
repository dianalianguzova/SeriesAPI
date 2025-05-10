package com.example.series.repository;

import com.example.series.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
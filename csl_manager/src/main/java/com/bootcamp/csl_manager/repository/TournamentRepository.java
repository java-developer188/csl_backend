package com.bootcamp.csl_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.csl_manager.entity.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}

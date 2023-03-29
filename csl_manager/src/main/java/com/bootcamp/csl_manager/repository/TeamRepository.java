package com.bootcamp.csl_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.csl_manager.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}

package com.bootcamp.csl_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.csl_manager.entity.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {

}

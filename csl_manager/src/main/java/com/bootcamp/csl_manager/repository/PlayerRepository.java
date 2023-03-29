package com.bootcamp.csl_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.csl_manager.entity.Player;

public interface PlayerRepository  extends JpaRepository<Player, Long>  {

}

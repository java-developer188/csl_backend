package com.bootcamp.csl_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bootcamp.csl_manager.entity.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
	
	@Query("SELECT max(match.id) FROM Match match")
	public long findMaxMatchId();
}

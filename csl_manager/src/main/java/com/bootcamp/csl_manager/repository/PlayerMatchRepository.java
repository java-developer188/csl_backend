package com.bootcamp.csl_manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bootcamp.csl_manager.entity.PlayerMatch;

public interface PlayerMatchRepository extends JpaRepository<PlayerMatch, Long> {
	
	@Query("SELECT playerMatch FROM PlayerMatch playerMatch WHERE playerMatch.match.id = ?1 AND playerMatch.player.id = ?2")
	public Optional<PlayerMatch> findByMatchIdandPlayerId(long match_id, long player_id);
	
	@Query("SELECT playerMatch FROM PlayerMatch playerMatch WHERE playerMatch.player.id = ?1")
	public Optional<List<PlayerMatch>> findByScorePlayerId(long player_id);
	
	@Query(value = "select id from (select avg(score), player_id as id from cslmanagerdb.tbl_player_match group by player_id order by avg(score) desc) as players;", nativeQuery = true)
	public Optional<List<String>> findDistinctPlayer();
}

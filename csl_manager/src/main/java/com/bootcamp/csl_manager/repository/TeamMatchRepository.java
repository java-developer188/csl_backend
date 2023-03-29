package com.bootcamp.csl_manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bootcamp.csl_manager.entity.TeamMatch;
import com.bootcamp.csl_manager.responsemodel.IPointsTableProjection;

public interface TeamMatchRepository  extends JpaRepository<TeamMatch, Long> {
	
	@Query("SELECT teamMatch FROM TeamMatch teamMatch WHERE teamMatch.team.id = ?1 AND teamMatch.match.id = ?2")
	public Optional<TeamMatch> findTeamMatchByTeamIdandMatchId(long team_id, long match_id);
	
	@Query(value = "SELECT id FROM cslmanagerdb.tbl_team WHERE sport_id = ?1 LIMIT ?2", nativeQuery = true)
	public Optional<List<String>> findTopTeams(long sport_id, int noOfTeam);
	
	@Query(value = "select id from cslmanagerdb.tbl_match limit 31;", nativeQuery = true)
	public Optional<List<String>> findMatchesForEightTeam();

	@Query("SELECT teamMatch FROM TeamMatch teamMatch WHERE teamMatch.match.id = ?1")
	public Optional<List<TeamMatch>> findByTeamMatchMatchId(long match_id);
	
	@Query(value = "select distinct match_id from cslmanagerdb.tbl_team_match;", nativeQuery = true)
	public Optional<List<String>> findDistinctMatch();
	
	@Query(value = "select distinct teamMatch.match_id from cslmanagerdb.tbl_team_match teamMatch join cslmanagerdb.tbl_team team on teamMatch.team_id = team.id  where sport_id = ?1", nativeQuery = true)
	public Optional<List<String>> findDistinctMatchBySportId(long sport_id);
	
	@Query(value = "select team.name, count(team_id) as played, count(case when is_winner = 'TRUE' then 1 end) as won ,count(case when is_winner = 'TRUE' then 1 end) *2 as points, sum(match_score) as score \r\n"
			+ "from  cslmanagerdb.tbl_team_match team_match \r\n"
			+ "join cslmanagerdb.tbl_team team on team_match.team_id = team.id \r\n"
			+ "where team.sport_id = ?1 \r\n"
			+ "group by team_id \r\n"
			+ "order by points desc, score desc;", nativeQuery = true)
	public Optional<List<IPointsTableProjection>> findTeamPointsTable(long sport_id);
	
}

package com.bootcamp.csl_manager.service;

import com.bootcamp.csl_manager.dto.GenerateFixtureDTO;
import com.bootcamp.csl_manager.dto.TeamMatchScore;
import com.bootcamp.csl_manager.utilities.ListResponse;
import com.bootcamp.csl_manager.utilities.SingleResponse;

public interface TeamMatchService {

	public SingleResponse add(long team_id, long match_id);
	public SingleResponse update(long team_id, long match_id, TeamMatchScore t);
	public SingleResponse generateFixture(GenerateFixtureDTO generateFixtureDTO);
	public ListResponse getAllFixture();
	public ListResponse getAllFixtureBySportId(long sport_id);
	public SingleResponse getMatchFixture(long match_id);
	public ListResponse getPointsTable(long sport_id);

}

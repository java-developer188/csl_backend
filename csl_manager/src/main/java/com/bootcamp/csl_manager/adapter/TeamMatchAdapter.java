package com.bootcamp.csl_manager.adapter;

import java.util.ArrayList;
import java.util.List;

import com.bootcamp.csl_manager.entity.TeamMatch;
import com.bootcamp.csl_manager.responsemodel.FixtureResponseModel;
import com.bootcamp.csl_manager.responsemodel.FixtureTeamResponseModel;

public class TeamMatchAdapter {
	
	public static FixtureResponseModel getFixtureResposeModel(List<TeamMatch> teamMatch) {

		FixtureResponseModel responseModel = new FixtureResponseModel();

		responseModel.setMatch(MatchAdapter.matchToMatchDto(teamMatch.get(0).getMatch()));
		
		List<FixtureTeamResponseModel> teamList = new ArrayList<>();

		for (TeamMatch item : teamMatch) {
			FixtureTeamResponseModel team = new FixtureTeamResponseModel();
			
			team.setName(item.getTeam().getName());
			team.setId(item.getTeam().getId());
			team.setDescription(item.getTeam().getDescription());
			team.setIsWinner(item.getIsWinner());
			team.setMatchScore(item.getMatchScore());
			
			teamList.add(team);
		}
		responseModel.setTeams(teamList);

		return responseModel;

	}
}

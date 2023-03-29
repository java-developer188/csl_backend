package com.bootcamp.csl_manager.adapter;

import com.bootcamp.csl_manager.dto.TeamDTO;
import com.bootcamp.csl_manager.entity.Team;
import com.bootcamp.csl_manager.responsemodel.TeamResponseModel;

public class TeamAdapter {
	
	public static TeamDTO teamToTeamDto(Team team) {

		if (team == null) {
			return null;
		}

		TeamDTO TeamDTO = new TeamDTO();

		TeamDTO.setId(team.getId());
		TeamDTO.setName(team.getName());
		TeamDTO.setDescription(team.getDescription());
		TeamDTO.setStatus(team.getStatus());
		TeamDTO.setSport(SportAdapter.sportToSportDto(team.getSport()));

		return TeamDTO;
	}

	public static Team teamDtoToTeam(TeamDTO TeamDTO) {

		if (TeamDTO == null) {
			return null;
		}

		Team team = new Team();

		team.setId(TeamDTO.getId());
		team.setName(TeamDTO.getName());
		team.setDescription(TeamDTO.getDescription());
		team.setStatus(TeamDTO.getStatus());
		team.setSport(SportAdapter.sportDtoToSport(TeamDTO.getSport()));

		return team;
	}
	
	public static TeamResponseModel getPlayerResponseModel(Team team) {

		TeamResponseModel teamResponseModel = new TeamResponseModel();

		teamResponseModel.setId(team.getId());
		teamResponseModel.setName(team.getName());
		teamResponseModel.setDescription(team.getDescription());
		teamResponseModel.setStatus(team.getStatus());
		String sport = team.getSport() != null ? team.getSport().getName() : null;
		teamResponseModel.setSport(sport);

		return teamResponseModel;
	}
}

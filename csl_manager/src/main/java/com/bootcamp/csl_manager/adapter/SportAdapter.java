package com.bootcamp.csl_manager.adapter;

import com.bootcamp.csl_manager.dto.SportDTO;
import com.bootcamp.csl_manager.entity.Sport;

public class SportAdapter {
	
	public static SportDTO sportToSportDto(Sport sport) {

		if(sport == null) {
			return null;
		}

		SportDTO sportDTO = new SportDTO();

		sportDTO.setId(sport.getId());
		sportDTO.setDescription(sport.getDescription());
		sportDTO.setName(sport.getName());
		sportDTO.setStatus(sport.getStatus());
		sportDTO.setTournament(TournamentAdapter.tournamentToTournamentDto(sport.getTournament()));

		return sportDTO;
	}

	public static Sport sportDtoToSport(SportDTO sportDTO) {

			if(sportDTO == null) {
				return null;
			}

			Sport sport = new Sport();

			sport.setId(sportDTO.getId());
			sport.setDescription(sportDTO.getDescription());
			sport.setName(sportDTO.getName());
			sport.setStatus(sportDTO.getStatus());
			sport.setTournament(TournamentAdapter.tournamentDtoToTournament(sportDTO.getTournament()));

			return sport;
		}

}

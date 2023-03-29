package com.bootcamp.csl_manager.adapter;

import com.bootcamp.csl_manager.dto.TournamentDTO;
import com.bootcamp.csl_manager.entity.Tournament;

public class TournamentAdapter {
	public static TournamentDTO tournamentToTournamentDto(Tournament tournament) {

		if (tournament == null) {
			return null;
		}

		TournamentDTO TournamentDTO = new TournamentDTO();

		TournamentDTO.setId(tournament.getId());
		TournamentDTO.setName(tournament.getName());
		TournamentDTO.setDescription(tournament.getDescription());
		TournamentDTO.setStatus(tournament.getStatus());

		return TournamentDTO;
	}

	public static Tournament tournamentDtoToTournament(TournamentDTO tournamentDTO) {

		if (tournamentDTO == null) {
			return null;
		}

		Tournament tournament = new Tournament();

		tournament.setId(tournamentDTO.getId());
		tournament.setName(tournamentDTO.getName());
		tournament.setDescription(tournamentDTO.getDescription());
		tournament.setStatus(tournamentDTO.getStatus());

		return tournament;
	}
}

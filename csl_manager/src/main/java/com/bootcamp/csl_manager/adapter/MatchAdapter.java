package com.bootcamp.csl_manager.adapter;

import com.bootcamp.csl_manager.dto.MatchDTO;
import com.bootcamp.csl_manager.entity.Match;

public class MatchAdapter {
	
	public static MatchDTO matchToMatchDto(Match match) {

		if (match == null) {
			return null;
		}

		MatchDTO matchDTO = new MatchDTO();

		matchDTO.setId(match.getId());
		matchDTO.setMatchNumber(match.getMatchNumber());
		matchDTO.setStartDateTime(match.getStartDateTime());
		matchDTO.setEndDateTime(match.getEndDateTime());
		matchDTO.setVenue(match.getVenue());
		matchDTO.setDescription(match.getDescription());
		
		return matchDTO;
	}

	public static Match matchDtoToMatch(MatchDTO matchDTO) {

		if (matchDTO == null) {
			return null;
		}

		Match match = new Match();

		match.setId(matchDTO.getId());
		match.setMatchNumber(matchDTO.getMatchNumber());
		match.setStartDateTime(matchDTO.getStartDateTime());
		match.setEndDateTime(matchDTO.getEndDateTime());
		match.setVenue(matchDTO.getVenue());
		match.setDescription(matchDTO.getDescription());

		return match;
	}

}

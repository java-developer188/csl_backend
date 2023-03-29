package com.bootcamp.csl_manager.service;

import com.bootcamp.csl_manager.dto.SportDTO;
import com.bootcamp.csl_manager.utilities.SingleResponse;

public interface SportService extends GenericService<SportDTO> {
	public SingleResponse linkSportWithTournament(long sport_id, long tournament_id);
}

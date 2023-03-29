package com.bootcamp.csl_manager.service;

import com.bootcamp.csl_manager.dto.TeamDTO;
import com.bootcamp.csl_manager.utilities.SingleResponse;

public interface TeamService extends GenericService<TeamDTO> {
	
	public SingleResponse linkTeamWithSport(long team_id, long sport_id);

}

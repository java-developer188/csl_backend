package com.bootcamp.csl_manager.service;

import com.bootcamp.csl_manager.dto.PlayerDTO;
import com.bootcamp.csl_manager.utilities.SingleResponse;

public interface PlayerService  extends GenericService<PlayerDTO> {
	
	public SingleResponse linkPlayerWithTeam(long player_id, long team_id);

}

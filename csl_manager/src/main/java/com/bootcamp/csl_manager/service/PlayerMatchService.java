package com.bootcamp.csl_manager.service;

import com.bootcamp.csl_manager.dto.PlayerMatchScore;
import com.bootcamp.csl_manager.utilities.ListResponse;
import com.bootcamp.csl_manager.utilities.SingleResponse;

public interface PlayerMatchService {
	
	public SingleResponse add(long match_id, long player_id, PlayerMatchScore t);
	public SingleResponse update(long match_id, long player_id, PlayerMatchScore t);
	public SingleResponse playerScore(long player_id);
	public ListResponse allPlayerScore();
}

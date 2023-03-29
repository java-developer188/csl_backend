package com.bootcamp.csl_manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.csl_manager.adapter.PlayerMatchAdapter;
import com.bootcamp.csl_manager.dto.PlayerMatchScore;
import com.bootcamp.csl_manager.entity.Match;
import com.bootcamp.csl_manager.entity.Player;
import com.bootcamp.csl_manager.entity.PlayerMatch;
import com.bootcamp.csl_manager.repository.MatchRepository;
import com.bootcamp.csl_manager.repository.PlayerMatchRepository;
import com.bootcamp.csl_manager.repository.PlayerRepository;
import com.bootcamp.csl_manager.responsemodel.PlayerScoreResponseModel;
import com.bootcamp.csl_manager.utilities.ListResponse;
import com.bootcamp.csl_manager.utilities.ResponseMessage;
import com.bootcamp.csl_manager.utilities.SingleResponse;

@Service
public class PlayerMatchServiceImp implements PlayerMatchService {

	@Autowired
	PlayerMatchRepository playerMatchRepository;
	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	MatchRepository matchRepository;
	
	@Override
	public SingleResponse add(long match_id, long player_id, PlayerMatchScore playerMatchScore) {
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			
			Optional<Match> match = matchRepository.findById(match_id);
			
			if(!match.isPresent()) {
				singleResponse.message = ResponseMessage.matchIdNotFound;
				singleResponse.data = playerMatchScore;
				return singleResponse; 
			}
			
			Optional<Player> player = playerRepository.findById(player_id);
			
			if(!player.isPresent()) {
				singleResponse.message = ResponseMessage.playerIdNotFound;
				singleResponse.data = playerMatchScore;
				return singleResponse; 
			}
			
			PlayerMatch playerMatch = new PlayerMatch();
			playerMatch.setMatch(match.get());
			playerMatch.setPlayer(player.get());
			playerMatch.setScore(playerMatchScore.getScore());
			playerMatch.setOwnGoal(playerMatchScore.getOwnGoal());
			
		    playerMatchRepository.save(playerMatch);
			
			singleResponse.message = ResponseMessage.added;
			singleResponse.data = playerMatchScore;
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}

	@Override
	public SingleResponse update(long match_id, long player_id, PlayerMatchScore playerMatchScore) {
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			
			Optional<PlayerMatch> playerMatch = playerMatchRepository.findByMatchIdandPlayerId(match_id, player_id);
			
			if(!playerMatch.isPresent()) {
				singleResponse.message = ResponseMessage.matchIdPlayerIdNotFound;
				singleResponse.data = playerMatchScore;
				return singleResponse; 
			}
			
			playerMatch.get().setScore(playerMatchScore.getScore());
			playerMatch.get().setOwnGoal(playerMatchScore.getOwnGoal());
			
			playerMatchRepository.save(playerMatch.get());
			
			singleResponse.message = ResponseMessage.updated;
			singleResponse.data = playerMatchScore;
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}
	
	@Override
	public SingleResponse playerScore(long player_id) {
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;
		PlayerScoreResponseModel responseModel = new PlayerScoreResponseModel();
		try {
			
			Optional<List<PlayerMatch>> playerMatches = playerMatchRepository.findByScorePlayerId(player_id);
			
			if(playerMatches.get().size() == 0) {
				singleResponse.message = ResponseMessage.playerIdNotFound;
				singleResponse.data = responseModel;
				return singleResponse; 
			}
			
			responseModel = PlayerMatchAdapter.getPlayerScoreResposeModel(playerMatches.get());
			
			singleResponse.message = ResponseMessage.updated;
			singleResponse.data = responseModel;
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}

	@Override
	public ListResponse allPlayerScore() {
		ListResponse listResponse = new ListResponse();
		listResponse.isError = true;
		try {
		 Optional<List<String>> distinctPlayer =	playerMatchRepository.findDistinctPlayer();
		 List<PlayerScoreResponseModel> allPlayerScore = new ArrayList<PlayerScoreResponseModel>();
		 
		
		 if(distinctPlayer.get().size() > 0) {
			 for (String item : distinctPlayer.get()) {
				 Optional<List<PlayerMatch>> playerMatches = playerMatchRepository.findByScorePlayerId( Long.parseLong(item));
				 allPlayerScore.add(PlayerMatchAdapter.getPlayerScoreResposeModel(playerMatches.get()));
			 }
			 listResponse.data = allPlayerScore;
			 listResponse.message = ResponseMessage.dataFetch;
			 listResponse.totalSize = allPlayerScore.size();
		 }else {
			 listResponse.data = allPlayerScore;
			 listResponse.message = ResponseMessage.dataNotFound;
			 return listResponse;
		 }
		 
		 listResponse.isError = false;
		}catch(Exception ex) {
			listResponse.isError = true;
			listResponse.message = "System Level Error";
			throw ex;
		}
		return listResponse;
	}

}

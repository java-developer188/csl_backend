package com.bootcamp.csl_manager.adapter;

import java.util.ArrayList;
import java.util.List;

import com.bootcamp.csl_manager.entity.PlayerMatch;
import com.bootcamp.csl_manager.responsemodel.PlayerMatchScoreResponseModel;
import com.bootcamp.csl_manager.responsemodel.PlayerScoreResponseModel;

public class PlayerMatchAdapter {
	
	public static PlayerScoreResponseModel getPlayerScoreResposeModel(List<PlayerMatch> playerMatch) {
		
		PlayerScoreResponseModel responseModel = new PlayerScoreResponseModel();
		
		responseModel.setName(playerMatch.get(0).getPlayer().getFirstName() + " " + playerMatch.get(0).getPlayer().getLastName());
		responseModel.setAge(playerMatch.get(0).getPlayer().getAge());
		String skill = playerMatch.get(0).getPlayer().getPlayerSikll() != null ? playerMatch.get(0).getPlayer().getPlayerSikll().getSkill() : null;
		responseModel.setSkill(skill); 
		responseModel.setIsCaptain(playerMatch.get(0).getPlayer().getIsCaptain());
		int totalGoal = 0, totalOwnGoal = 0;
		List<PlayerMatchScoreResponseModel> matchScores = new ArrayList<>();
		
		for (PlayerMatch item : playerMatch) {
			PlayerMatchScoreResponseModel score = new PlayerMatchScoreResponseModel();
			score.setMatchNo(item.getMatch().getMatchNumber());
			score.setMatchGoal(item.getScore());
			score.setMatchOwnGoal(item.getOwnGoal());
			score.setMatchDiscription(item.getMatch().getDescription());
			totalGoal +=item.getScore();
			totalOwnGoal +=item.getOwnGoal();
			matchScores.add(score);
		}
		responseModel.setName(responseModel.getName().trim());
		responseModel.setMatches(matchScores);
		responseModel.setTotalGoal(totalGoal);
		responseModel.setTotalMatch(playerMatch.size());
		double goalAvg = responseModel.getTotalGoal()/responseModel.getTotalMatch();
		responseModel.setGoalAverage(goalAvg);
		responseModel.setTotalOwnGoal(totalOwnGoal);
		
		return responseModel;
		
	}
}

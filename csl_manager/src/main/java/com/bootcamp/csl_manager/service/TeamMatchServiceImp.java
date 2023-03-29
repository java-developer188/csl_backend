package com.bootcamp.csl_manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.csl_manager.adapter.TeamMatchAdapter;
import com.bootcamp.csl_manager.algorithms.FixtureAlgorithmFactory;
import com.bootcamp.csl_manager.algorithms.IFixtureAlgorithms;
import com.bootcamp.csl_manager.dto.GenerateFixtureDTO;
import com.bootcamp.csl_manager.dto.TeamMatchScore;
import com.bootcamp.csl_manager.entity.Match;
import com.bootcamp.csl_manager.entity.Sport;
import com.bootcamp.csl_manager.entity.Team;
import com.bootcamp.csl_manager.entity.TeamMatch;
import com.bootcamp.csl_manager.repository.MatchRepository;
import com.bootcamp.csl_manager.repository.SportRepository;
import com.bootcamp.csl_manager.repository.TeamMatchRepository;
import com.bootcamp.csl_manager.repository.TeamRepository;
import com.bootcamp.csl_manager.responsemodel.FixtureResponseModel;
import com.bootcamp.csl_manager.responsemodel.IPointsTableProjection;
import com.bootcamp.csl_manager.responsemodel.PointsTableResponseModel;
import com.bootcamp.csl_manager.utilities.ListResponse;
import com.bootcamp.csl_manager.utilities.ResponseMessage;
import com.bootcamp.csl_manager.utilities.SingleResponse;

@Service
public class TeamMatchServiceImp implements TeamMatchService {

	@Autowired
	TeamMatchRepository teamMatchRepository;
	@Autowired
	TeamRepository teamRepository;
	@Autowired
	MatchRepository matchRepository;
	@Autowired
	SportRepository sportRepository;
	@Autowired
	FixtureAlgorithmFactory fixtureAlgorithmFactory;
	
	@Override
	public SingleResponse add(long team_id, long match_id) {
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			
			Optional<Match> match = matchRepository.findById(match_id);
			
			if(!match.isPresent()) {
				singleResponse.message = ResponseMessage.matchIdNotFound;
				singleResponse.data = null;
				return singleResponse; 
			}
			
			Optional<Team> team = teamRepository.findById(team_id);
			
			if(!team.isPresent()) {
				singleResponse.message = ResponseMessage.teamIdNotFound;
				singleResponse.data = null;
				return singleResponse; 
			}
			
			Optional<TeamMatch> isMatchadded = teamMatchRepository.findTeamMatchByTeamIdandMatchId(team_id, match_id);
			
			if(isMatchadded.isPresent()) {
				singleResponse.message = ResponseMessage.matchAlreadyAdded;
				singleResponse.data = null;
				return singleResponse; 
			}
			
			TeamMatchScore teamMatchScore = new TeamMatchScore();
			TeamMatch teamMatch = new TeamMatch();
			teamMatch.setMatch(match.get());
			teamMatch.setTeam(team.get());
			teamMatch.setMatchScore(teamMatchScore.getMatchScore());
			teamMatch.setIsWinner(teamMatchScore.getIsWinner());
			
			teamMatchRepository.save(teamMatch);
			
			singleResponse.message = ResponseMessage.added;
			singleResponse.data = teamMatchScore;
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}

	@Override
	public SingleResponse update(long team_id, long match_id, TeamMatchScore teamMatchScore) {
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			
			Optional<TeamMatch> teamMatch = teamMatchRepository.findTeamMatchByTeamIdandMatchId(team_id, match_id);
			
			if(!teamMatch.isPresent()) {
				singleResponse.message = ResponseMessage.teamIdMatchIdNotFound;
				singleResponse.data = teamMatchScore;
				return singleResponse; 
			}
			
			teamMatch.get().setMatchScore(teamMatchScore.getMatchScore());
			teamMatch.get().setIsWinner(teamMatchScore.getIsWinner());
			
			teamMatchRepository.save(teamMatch.get());
			
			singleResponse.message = ResponseMessage.updated;
			singleResponse.data = teamMatchScore;
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}

	@Override
	public SingleResponse generateFixture(GenerateFixtureDTO generateFixtureDTO) {
		
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			
			Optional<Sport> sport = sportRepository.findById(generateFixtureDTO.getSportId());

			if (!sport.isPresent()) {
				singleResponse.message = ResponseMessage.sportIdNotFound;
				return singleResponse;
			}
			
			IFixtureAlgorithms algorithms = fixtureAlgorithmFactory.getAlogrithms(generateFixtureDTO.getFixtureAlgorithms());
			singleResponse = algorithms.algo(generateFixtureDTO); 
			
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}
	
	@Override
	public ListResponse getAllFixture() {
		ListResponse listResponse = new ListResponse();
		listResponse.isError = true;
		try {
		 Optional<List<String>> distinctMatch =	teamMatchRepository.findDistinctMatch();
		 List<FixtureResponseModel> allMatches = new ArrayList<FixtureResponseModel>();
		 		
		 if(distinctMatch.get().size() > 0) {
			 for (String item : distinctMatch.get()) {
				 Optional<List<TeamMatch>> playerMatches = teamMatchRepository.findByTeamMatchMatchId(Long.parseLong(item));
				 allMatches.add(TeamMatchAdapter.getFixtureResposeModel(playerMatches.get()));
			 }
			 listResponse.data = allMatches;
			 listResponse.message = ResponseMessage.dataFetch;
			 listResponse.totalSize = allMatches.size();
		 }else {
			 listResponse.data = allMatches;
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
	
	
	@Override
	public ListResponse getAllFixtureBySportId(long sport_id) {
		ListResponse listResponse = new ListResponse();
		listResponse.isError = true;
		try {
			
			Optional<Sport> sport = sportRepository.findById(sport_id);

			if (!sport.isPresent()) {
				listResponse.message = ResponseMessage.sportIdNotFound;
				return listResponse;
			}
			
		 Optional<List<String>> distinctMatch =	teamMatchRepository.findDistinctMatchBySportId(sport_id);
		 List<FixtureResponseModel> allMatches = new ArrayList<FixtureResponseModel>();
		 	
		 if(distinctMatch.get().size() > 0) {
			 for (String item : distinctMatch.get()) {
				 Optional<List<TeamMatch>> playerMatches = teamMatchRepository.findByTeamMatchMatchId(Long.parseLong(item));
				 allMatches.add(TeamMatchAdapter.getFixtureResposeModel(playerMatches.get()));
			 }
			 listResponse.data = allMatches;
			 listResponse.message = ResponseMessage.dataFetch;
			 listResponse.totalSize = allMatches.size();
		 }else {
			 listResponse.data = allMatches;
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
	
	@Override
	public SingleResponse getMatchFixture(long match_id) {
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;
		try {
			FixtureResponseModel matchFixture = new FixtureResponseModel();
		 Optional<List<TeamMatch>> playerMatches = teamMatchRepository.findByTeamMatchMatchId(match_id);
		 
		 if(playerMatches.get().size() > 0) {
			 matchFixture = TeamMatchAdapter.getFixtureResposeModel(playerMatches.get());
			 singleResponse.data = matchFixture;
			 singleResponse.message = ResponseMessage.dataFetch;
		 }else {
			 singleResponse.data = matchFixture;
			 singleResponse.message = ResponseMessage.dataNotFound;
			 return singleResponse;
		 }
		 
		 singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error";
			throw ex;
		}
		return singleResponse;
	}

	@Override
	public ListResponse getPointsTable(long sport_id) {
		ListResponse listResponse = new ListResponse();
		listResponse.isError = true;
		try {
			
			Optional<Sport> sport = sportRepository.findById(sport_id);

			if (!sport.isPresent()) {
				listResponse.message = ResponseMessage.sportIdNotFound;
				return listResponse;
			}
			
			Optional<List<IPointsTableProjection>> pointsTable = teamMatchRepository.findTeamPointsTable(sport_id);
		 
			List<PointsTableResponseModel> pointsTableList = new ArrayList<>();
		
		 if(pointsTable.get().size() > 0) {
			 for (IPointsTableProjection item : pointsTable.get()) {
				 PointsTableResponseModel point = new PointsTableResponseModel();
				 point.setName(item.getName());
				 point.setPlayed(item.getPlayed());
				 point.setWon(item.getWon());
				 point.setPoints(item.getPoints());
				 point.setScore(item.getScore());
				 pointsTableList.add(point);
			 }
			 listResponse.data = pointsTableList;
			 listResponse.message = ResponseMessage.dataFetch;
			 listResponse.totalSize = pointsTable.get().size();
		 }else {
			 listResponse.data = pointsTableList;
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

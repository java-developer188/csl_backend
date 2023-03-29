package com.bootcamp.csl_manager.common;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bootcamp.csl_manager.adapter.MatchAdapter;
import com.bootcamp.csl_manager.dto.GenerateFixtureDTO;
import com.bootcamp.csl_manager.dto.MatchDTO;
import com.bootcamp.csl_manager.dto.TeamMatchScore;
import com.bootcamp.csl_manager.entity.Match;
import com.bootcamp.csl_manager.entity.Team;
import com.bootcamp.csl_manager.entity.TeamMatch;
import com.bootcamp.csl_manager.repository.MatchRepository;
import com.bootcamp.csl_manager.repository.TeamMatchRepository;
import com.bootcamp.csl_manager.repository.TeamRepository;
import com.bootcamp.csl_manager.utilities.ResponseMessage;
import com.bootcamp.csl_manager.utilities.SingleResponse;

public class FixtureAlgos {
	
//	public FixtureAlgos() {
//		
//	}
//	
//	@Autowired
//	TeamMatchRepository teamMatchRepository;
//	@Autowired
//	MatchRepository matchRepository;
//	@Autowired
//	TeamRepository teamRepository;
//	
//	public SingleResponse RoundRobinWithoutGroup (GenerateFixtureDTO generateFixtureDTO) {
//		
//		SingleResponse singleResponse = new SingleResponse();
//		singleResponse.isError = true;
//		
//		if(generateFixtureDTO.getNoOfTeams()%2 == 1) {
//
//			singleResponse.message = ResponseMessage.teamInEven;
//			singleResponse.data = null;
//			return singleResponse; 
//		}
//		
//		Optional<List<String>> totalTeams =	teamMatchRepository.findTopTeams( generateFixtureDTO.getSportId(),generateFixtureDTO.getNoOfTeams());
//		String[] totalTeamArray = totalTeams.get().toArray(new String[0]);
//		
//		if(totalTeamArray.length < generateFixtureDTO.getNoOfTeams()) {
//
//			singleResponse.message = ResponseMessage.enoughTeam;
//			singleResponse.data = null;
//			return singleResponse; 
//		}
//		
//		RoundRobin(totalTeamArray, generateFixtureDTO);
//        
//		singleResponse.message = ResponseMessage.fixtureGenerated;
//		singleResponse.isError = false;
//        return singleResponse;
//	}
//	
//	public static void RoundRobinWithGroup () {
//		
//	}
//	
//	public void RoundRobin(String[] totalTeamArray, GenerateFixtureDTO generateFixtureDTO) {
//		
//		int group = generateFixtureDTO.getNoOfTeams()/2;
//		int rounds = generateFixtureDTO.getNoOfTeams()-1;
//		
//		
//		String[] group1 = Arrays.copyOfRange(totalTeamArray, 0, group);
//        String[] group2 = Arrays.copyOfRange(totalTeamArray, group, generateFixtureDTO.getNoOfTeams());
//		
//		// Generate matches for each round
//        for (int round = 1; round <= rounds; round++) {
//
//            // Generate matches between the teams in each group
//            for (int i = 0; i < group1.length; i++) {
//                
//                TeamMatchScore teamMatchScore = new TeamMatchScore();
//    			TeamMatch teamMatch = new TeamMatch();
//    			
//    			MatchDTO matchDTO = new MatchDTO();
//    			Match newMatch = MatchAdapter.matchDtoToMatch(matchDTO);
//    		    matchRepository.save(newMatch);
//    			
//    			Long matchId = matchRepository.findMaxMatchId();
//    		    
//    		    matchDTO.setId(matchId);
//    		    matchDTO.setMatchNumber(matchId.intValue());
//    		    matchDTO.setDescription("round Match");
//    		    matchDTO.setStartDateTime(null);
//    		    matchDTO.setEndDateTime(null);
//    		    matchDTO.setVenue("TBA");
//    		    Match match = MatchAdapter.matchDtoToMatch(matchDTO);
//    		    matchRepository.save(match);
//    			
//    			Optional<Team> team = teamRepository.findById(Long.parseLong(group1[i]));
//    			
//    			teamMatch.setMatch(match);
//    			teamMatch.setTeam(team.get());
//    			teamMatch.setMatchScore(teamMatchScore.getMatchScore());
//    			teamMatch.setIsWinner(teamMatchScore.getIsWinner());
//    			teamMatchRepository.save(teamMatch);
//    			
//    			TeamMatch teamMatch2 = new TeamMatch();
//    			
//    			Optional<Team> team2 = teamRepository.findById(Long.parseLong(group2[i]));
//    			
//    			teamMatch2.setMatch(match);
//    			teamMatch2.setTeam(team2.get());
//    			teamMatch2.setMatchScore(teamMatchScore.getMatchScore());
//    			teamMatch2.setIsWinner(teamMatchScore.getIsWinner());
//    			teamMatchRepository.save(teamMatch2);
//    			
//            }
//
//            // Rotate the teams in each group to generate the next round of matches
//            rotateArray(group1);
//            rotateArray(group2);
//        }
//	}
//	
//	// Helper method to rotate an array
//    private static void rotateArray(String[] arr) {
//        String last = arr[arr.length - 1];
//        System.arraycopy(arr, 0, arr, 1, arr.length - 1);
//        arr[0] = last;
//    }

}

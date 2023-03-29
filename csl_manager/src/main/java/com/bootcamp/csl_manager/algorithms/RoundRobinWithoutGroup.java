package com.bootcamp.csl_manager.algorithms;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootcamp.csl_manager.dto.GenerateFixtureDTO;
import com.bootcamp.csl_manager.dto.TeamMatchScore;
import com.bootcamp.csl_manager.entity.Match;
import com.bootcamp.csl_manager.entity.Team;
import com.bootcamp.csl_manager.entity.TeamMatch;
import com.bootcamp.csl_manager.repository.MatchRepository;
import com.bootcamp.csl_manager.repository.TeamMatchRepository;
import com.bootcamp.csl_manager.repository.TeamRepository;
import com.bootcamp.csl_manager.utilities.ResponseMessage;
import com.bootcamp.csl_manager.utilities.SingleResponse;

@Component
public class RoundRobinWithoutGroup implements IFixtureAlgorithms {

	@Autowired
	TeamMatchRepository teamMatchRepository;
	@Autowired
	MatchRepository matchRepository;
	@Autowired
	TeamRepository teamRepository;

	@Override
	public SingleResponse algo(GenerateFixtureDTO generateFixtureDTO) {
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		if (generateFixtureDTO.getNoOfTeams() % 2 == 1) {

			singleResponse.message = ResponseMessage.teamInEven;
			singleResponse.data = null;
			return singleResponse;
		}

		Optional<List<String>> totalTeams = teamMatchRepository.findTopTeams(generateFixtureDTO.getSportId(),
				generateFixtureDTO.getNoOfTeams());
		String[] totalTeamArray = totalTeams.get().toArray(new String[0]);

		if (totalTeamArray.length < generateFixtureDTO.getNoOfTeams()) {

			singleResponse.message = ResponseMessage.enoughTeam;
			singleResponse.data = null;
			return singleResponse;
		}

		int numberOfTeams = generateFixtureDTO.getNoOfTeams();

		int totalMatches = (generateFixtureDTO.getNoOfTeams() / 2) * (numberOfTeams - 1);

		Map<Integer, List<LocalDateTime>> matchNumberWithDateTime = new HashMap<Integer, List<LocalDateTime>>();

		try {
			calculateMatchTiming(matchNumberWithDateTime, totalMatches, generateFixtureDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Shuffle the keys of the map
        List<Integer> matchNumber = new ArrayList<>(matchNumberWithDateTime.keySet());
        Collections.shuffle(matchNumber);
        Map<Integer, List<LocalDateTime>> shuffledMmatchNumberWithDateTime = new HashMap<>();
        for (Integer key : matchNumber) {
        	shuffledMmatchNumberWithDateTime.put(key, matchNumberWithDateTime.get(key));
        }

		int matchItrator = 0;

	        for (int i = 0; i < numberOfTeams - 1; i++) {
	            for (int j = i+1; j < numberOfTeams; j++) {
     
					TeamMatchScore teamMatchScore = new TeamMatchScore();
					
					Match match = new Match();
	
					match.setMatchNumber(matchNumber.get(matchItrator));
					match.setDescription("Round Match");
					match.setStartDateTime(shuffledMmatchNumberWithDateTime.get(matchNumber.get(matchItrator)).get(0));
					match.setEndDateTime(shuffledMmatchNumberWithDateTime.get(matchNumber.get(matchItrator)).get(1));
					match.setVenue("TBA");
					matchRepository.save(match);
	
					TeamMatch teamMatch = new TeamMatch();
					
					Optional<Team> team = teamRepository.findById(Long.parseLong(totalTeamArray[i]));
	
					teamMatch.setMatch(match);
					teamMatch.setTeam(team.get());
					teamMatch.setMatchScore(teamMatchScore.getMatchScore());
					teamMatch.setIsWinner(teamMatchScore.getIsWinner());
					teamMatchRepository.save(teamMatch);
	
					TeamMatch teamMatch2 = new TeamMatch();
	
					Optional<Team> team2 = teamRepository.findById(Long.parseLong(totalTeamArray[j]));
	
					teamMatch2.setMatch(match);
					teamMatch2.setTeam(team2.get());
					teamMatch2.setMatchScore(teamMatchScore.getMatchScore());
					teamMatch2.setIsWinner(teamMatchScore.getIsWinner());
					teamMatchRepository.save(teamMatch2);
	                
					matchItrator++;
	                
	            }
	        }
		singleResponse.message = ResponseMessage.fixtureGenerated;
		singleResponse.isError = false;
		return singleResponse;
	}

	public void calculateMatchTiming(Map<Integer, List<LocalDateTime>> matchNumberWithDateTime, int totalMatches,
			GenerateFixtureDTO generateFixtureDTO) throws Exception {

		LocalDateTime matchStartDateTime = generateFixtureDTO.getMatchStartDate();
		LocalDateTime matchEndDateTime;
		String dateString;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    dateString = matchStartDateTime.format(formatter);
		LocalDateTime startDateTime = LocalDateTime.parse(dateString, formatter);
		int matchPerDayCounter = 1;
		int dayCount = 1;
		for (int i = 1; i <= totalMatches; i++) {

			List<LocalDateTime> matchDateTime = new ArrayList<LocalDateTime>();
			matchDateTime.add(startDateTime);
			
			matchEndDateTime = startDateTime.plusMinutes(generateFixtureDTO.getMatchDuration());
			
			matchDateTime.add(matchEndDateTime);
			
			startDateTime = matchEndDateTime.plusMinutes(generateFixtureDTO.getBreakBetweenMatches());
			
			matchNumberWithDateTime.put(i, matchDateTime);
			
			if(matchPerDayCounter == generateFixtureDTO.getNoOfMatchPerDay()) {
				
				DayOfWeek dayOfWeek = startDateTime.getDayOfWeek(); // get the day of the week
				if (dayOfWeek == DayOfWeek.FRIDAY) {  //to avoid set match on weekend days
					dayCount = dayCount + 2;
				}
				
				startDateTime = matchStartDateTime.plusDays(dayCount); //jump into next day
				dayCount++;
				matchPerDayCounter = 0;
			}
			
			matchPerDayCounter++;
		}

	}
}

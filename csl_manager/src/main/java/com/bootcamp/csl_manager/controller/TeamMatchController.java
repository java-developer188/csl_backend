package com.bootcamp.csl_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.csl_manager.dto.GenerateFixtureDTO;
import com.bootcamp.csl_manager.dto.PlayerMatchScore;
import com.bootcamp.csl_manager.dto.TeamMatchScore;
import com.bootcamp.csl_manager.responsemodel.FixtureResponseModel;
import com.bootcamp.csl_manager.responsemodel.PlayerScoreResponseModel;
import com.bootcamp.csl_manager.responsemodel.PointsTableResponseModel;
import com.bootcamp.csl_manager.service.TeamMatchService;
import com.bootcamp.csl_manager.utilities.ListResponse;
import com.bootcamp.csl_manager.utilities.SingleResponse;
import com.bootcamp.csl_manager.utilities.URLs;

@RestController
@RequestMapping(URLs.VERSION1)
@CrossOrigin(value="*")
public class TeamMatchController {
	
	@Autowired
	TeamMatchService teamMatchService;
	
	@PostMapping( URLs.MatchWithID + "/" + URLs.TeamWithID)
	public ResponseEntity<SingleResponse> add(@PathVariable long match_id, @PathVariable long team_id,@RequestBody TeamMatchScore teamMatchScore){
		SingleResponse response = new SingleResponse();
		try {
			response = teamMatchService.add(team_id, match_id);
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<SingleResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SingleResponse>(response,HttpStatus.OK);
	}
	
	@PutMapping(URLs.MatchWithID +"/"+ URLs.TeamWithID + URLs.Result)
	public ResponseEntity<SingleResponse> update(@PathVariable long match_id, @PathVariable long team_id,@RequestBody TeamMatchScore teamMatchScore){
		SingleResponse response = new SingleResponse();
		try {
			response = teamMatchService.update(team_id, match_id, teamMatchScore);
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<SingleResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SingleResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping(URLs.Team +"/"+ URLs.Match + "/" + URLs.Fixture)
	public ResponseEntity<SingleResponse> generateFixture(@RequestBody GenerateFixtureDTO generateFixtureDTO){
		SingleResponse response = new SingleResponse();
		try {
			response = teamMatchService.generateFixture(generateFixtureDTO);
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<SingleResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SingleResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping(URLs.Fixture)
	public ResponseEntity<ListResponse> getFixtures(){
		ListResponse response = new ListResponse();
		try {
			response = teamMatchService.getAllFixture();
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<ListResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ListResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping(URLs.Sport + URLs.SportID +URLs.Fixture)
	public ResponseEntity<ListResponse> getFixturesBySportId(@PathVariable long sport_id){
		ListResponse response = new ListResponse();
		try {
			response = teamMatchService.getAllFixtureBySportId(sport_id);
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<ListResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ListResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping(URLs.MatchWithID + URLs.Fixture)
	public ResponseEntity<SingleResponse> getMatchFixture(@PathVariable long match_id){
		SingleResponse response = new SingleResponse();
		try {
			response = teamMatchService.getMatchFixture(match_id);
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<SingleResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SingleResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping(URLs.Sport + URLs.SportID + "/"+ URLs.PointTable)
	public ResponseEntity<ListResponse> getPointsTable(@PathVariable long sport_id){
		ListResponse response = new ListResponse();
		try {
			response = teamMatchService.getPointsTable(sport_id);
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<ListResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ListResponse>(response,HttpStatus.OK);
	}
}

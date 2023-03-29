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

import com.bootcamp.csl_manager.dto.PlayerMatchScore;
import com.bootcamp.csl_manager.responsemodel.PlayerScoreResponseModel;
import com.bootcamp.csl_manager.service.PlayerMatchService;
import com.bootcamp.csl_manager.utilities.ListResponse;
import com.bootcamp.csl_manager.utilities.SingleResponse;
import com.bootcamp.csl_manager.utilities.URLs;

@RestController
@RequestMapping(URLs.VERSION1)
@CrossOrigin(value="*")
public class PlayerMatchController {
	
	@Autowired
	PlayerMatchService playerMatchService;
	
	@PostMapping(URLs.PlayerWithID + "/" + URLs.MatchWithID + URLs.score)
	public ResponseEntity<SingleResponse> add(@PathVariable long player_id, @PathVariable long match_id,@RequestBody PlayerMatchScore playerMatchScore){
		SingleResponse response = new SingleResponse();
		try {
			response = playerMatchService.add(match_id, player_id, playerMatchScore);
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<SingleResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SingleResponse>(response,HttpStatus.OK);
	}
	
	@PutMapping(URLs.PlayerWithID +"/"+ URLs.MatchWithID + URLs.score)
	public ResponseEntity<SingleResponse> update(@PathVariable long player_id, @PathVariable long match_id,@RequestBody PlayerMatchScore playerMatchScore){
		SingleResponse response = new SingleResponse();
		try {
			response = playerMatchService.update(match_id, player_id, playerMatchScore);
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<SingleResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SingleResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping(URLs.PlayerWithID +URLs.score)
	public ResponseEntity<SingleResponse> playerScore(@PathVariable long player_id){
		SingleResponse response = new SingleResponse();
		try {
			response = playerMatchService.playerScore(player_id);
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<SingleResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SingleResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping(URLs.Player +URLs.score)
	public ResponseEntity<ListResponse> allPlayerScore(){
		ListResponse response = new ListResponse();
		try {
			response = playerMatchService.allPlayerScore();
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<ListResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ListResponse>(response,HttpStatus.OK);
	}
	
}

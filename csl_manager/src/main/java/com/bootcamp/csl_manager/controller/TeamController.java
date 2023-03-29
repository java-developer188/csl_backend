package com.bootcamp.csl_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.csl_manager.dto.TeamDTO;
import com.bootcamp.csl_manager.service.TeamService;
import com.bootcamp.csl_manager.utilities.ListResponse;
import com.bootcamp.csl_manager.utilities.SingleResponse;
import com.bootcamp.csl_manager.utilities.URLs;

@RestController
@RequestMapping(URLs.VERSION1 + URLs.Team)
@CrossOrigin(value="*")
public class TeamController {
	
	@Autowired
	TeamService teamService;
	
	@GetMapping(URLs.GetAll)
	public ResponseEntity<ListResponse> findAll(){
		ListResponse response = new ListResponse();
		try {
			response = teamService.findAll();
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<ListResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ListResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping(URLs.GetByID)
	public ResponseEntity<SingleResponse> findById(@PathVariable long id){
		SingleResponse response = new SingleResponse();
		try {
			response = teamService.findById(id);
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<SingleResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SingleResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping(URLs.Create)
	public ResponseEntity<SingleResponse> create(@RequestBody TeamDTO teamDTO){
		SingleResponse response = new SingleResponse();
		try {
			response = teamService.create(teamDTO);
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<SingleResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SingleResponse>(response,HttpStatus.OK);
	}
	
	@PutMapping(URLs.Update)
	public ResponseEntity<SingleResponse> update(@PathVariable long id, @RequestBody TeamDTO teamDTO){
		SingleResponse response = new SingleResponse();
		try {
			response = teamService.update(id, teamDTO);
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<SingleResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SingleResponse>(response,HttpStatus.OK);
	}
	
	@DeleteMapping(URLs.Delete)
	public ResponseEntity<SingleResponse> delete(@PathVariable long id){
		SingleResponse response = new SingleResponse();
		try {
			response = teamService.deleteById(id);
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<SingleResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SingleResponse>(response,HttpStatus.OK);
	}
	
	@PutMapping(URLs.TeamId + "/" + URLs.Sport + "/" + URLs.SportID + URLs.Link)
	public ResponseEntity<SingleResponse> linkPlayerWithTeam(@PathVariable long team_id, @PathVariable long sport_id){
		SingleResponse response = new SingleResponse();
		try {
			response = teamService.linkTeamWithSport(team_id, sport_id);
		}catch(Exception ex) {
			response.isError = true;
			response.message = "System Level Error";
			response.data = null;
			return new ResponseEntity<SingleResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SingleResponse>(response,HttpStatus.OK);
	}
}

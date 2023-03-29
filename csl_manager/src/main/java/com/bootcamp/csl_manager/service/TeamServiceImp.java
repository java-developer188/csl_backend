package com.bootcamp.csl_manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.csl_manager.adapter.SportAdapter;
import com.bootcamp.csl_manager.adapter.TeamAdapter;
import com.bootcamp.csl_manager.dto.TeamDTO;
import com.bootcamp.csl_manager.entity.Sport;
import com.bootcamp.csl_manager.entity.Team;
import com.bootcamp.csl_manager.repository.SportRepository;
import com.bootcamp.csl_manager.repository.TeamRepository;
import com.bootcamp.csl_manager.responsemodel.TeamResponseModel;
import com.bootcamp.csl_manager.utilities.ListResponse;
import com.bootcamp.csl_manager.utilities.ResponseMessage;
import com.bootcamp.csl_manager.utilities.SingleResponse;

@Service
public class TeamServiceImp implements TeamService {
	
	@Autowired
	TeamRepository teamRepository;
	@Autowired
	SportRepository sportRepository;
	
	@Override
	public ListResponse findAll() {
		ListResponse listResponse = new ListResponse();
		listResponse.isError = true;
		try {
		 List<Team> teams =	teamRepository.findAll();
		 List<TeamResponseModel> teamResponseModel = new ArrayList<TeamResponseModel>();
		 
		 if(teams.size() > 0) {
			 for (Team player : teams) {
				 teamResponseModel.add(TeamAdapter.getPlayerResponseModel(player));
			 }
			 
			 listResponse.data = teamResponseModel;
			 listResponse.message = ResponseMessage.dataFetch;
			 listResponse.totalSize = teamResponseModel.size();
		 }else {
			 listResponse.data = teamResponseModel;
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
	public SingleResponse findById(long id) {
		
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;
		
		try {
			Optional<Team> team =	teamRepository.findById(id);
			
			 if(team.isPresent()) {
				 singleResponse.data = TeamAdapter.getPlayerResponseModel(team.get());
				 singleResponse.message = ResponseMessage.dataFetch;
			 }else {
				 singleResponse.data = null;
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
	public SingleResponse create(TeamDTO teamDTO) {
		
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			
			if(teamDTO.getSport() != null) {
				Optional<Sport> sport = sportRepository.findById(teamDTO.getSport().getId());
				if(!sport.isPresent()) {
					singleResponse.message = ResponseMessage.sportIdNotFound;
					return singleResponse;
				}
				teamDTO.setSport(SportAdapter.sportToSportDto(sport.get()));
			}
			
			Team team = teamRepository.save(TeamAdapter.teamDtoToTeam(teamDTO));
			singleResponse.message = ResponseMessage.added;
			singleResponse.data =  TeamAdapter.getPlayerResponseModel(team);
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}

	@Override
	public SingleResponse update(long id, TeamDTO teamDTO) {
		
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			Optional<Team> team =	teamRepository.findById(id);
			
			if(!team.isPresent()) {
				singleResponse.message = ResponseMessage.teamIdNotFound;
				singleResponse.data = teamDTO;
				return singleResponse;
			}
			
			if(teamDTO.getSport() != null) {
				Optional<Sport> sport = sportRepository.findById(teamDTO.getSport().getId());
				if(!sport.isPresent()) {
					singleResponse.message = ResponseMessage.sportIdNotFound;
					return singleResponse;
				}
				teamDTO.setSport(SportAdapter.sportToSportDto(sport.get()));
			}else {
				if(team.get().getSport() !=null) {
					teamDTO.setSport(SportAdapter.sportToSportDto(team.get().getSport()));
				}
			}
			

			Team updatedTeam = TeamAdapter.teamDtoToTeam(teamDTO);
			updatedTeam.setId(id);
			teamRepository.save(updatedTeam);
			singleResponse.data = TeamAdapter.getPlayerResponseModel(updatedTeam);
			singleResponse.message = ResponseMessage.updated;
			
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}

	@Override
	public SingleResponse deleteById(long id) {
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;
		try {
			Optional<Team> team =	teamRepository.findById(id);
			
			 if(team.isPresent()) {
				 teamRepository.deleteById(id);
				 singleResponse.message = ResponseMessage.deleted;
			 }else {
				 singleResponse.data = null;
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
	public SingleResponse linkTeamWithSport(long team_id, long sport_id) {
		
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			
			Optional<Team> team =	teamRepository.findById(team_id);
			
			if(!team.isPresent()) {
				singleResponse.message = ResponseMessage.teamIdNotFound;
				singleResponse.data = null;
				return singleResponse;
			}
			
			Optional<Sport> sport = sportRepository.findById(sport_id);
			
			if(!sport.isPresent()) {
				singleResponse.message = ResponseMessage.sportIdNotFound;
				return singleResponse;
			}
			
			team.get().setSport(sport.get());
			teamRepository.save(team.get());
			singleResponse.data = TeamAdapter.getPlayerResponseModel(team.get());
			singleResponse.message = ResponseMessage.teamLinkedWithSport;
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}
}

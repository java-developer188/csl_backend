package com.bootcamp.csl_manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.csl_manager.adapter.PlayerAdapter;
import com.bootcamp.csl_manager.adapter.PlayerSkillAdapter;
import com.bootcamp.csl_manager.adapter.TeamAdapter;
import com.bootcamp.csl_manager.dto.PlayerDTO;
import com.bootcamp.csl_manager.entity.Player;
import com.bootcamp.csl_manager.entity.PlayerSkill;
import com.bootcamp.csl_manager.entity.Team;
import com.bootcamp.csl_manager.repository.PlayerRepository;
import com.bootcamp.csl_manager.repository.PlayerSkillRepository;
import com.bootcamp.csl_manager.repository.TeamRepository;
import com.bootcamp.csl_manager.responsemodel.PlayerResponseModel;
import com.bootcamp.csl_manager.utilities.ListResponse;
import com.bootcamp.csl_manager.utilities.ResponseMessage;
import com.bootcamp.csl_manager.utilities.SingleResponse;

@Service
public class PlayerServiceImp implements PlayerService{

	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	TeamRepository teamRepository;
	@Autowired
	PlayerSkillRepository playerSkillRepository;
	
	@Override
	public ListResponse findAll() {
		ListResponse listResponse = new ListResponse();
		listResponse.isError = true;
		try {
		 List<Player> players =	playerRepository.findAll();
		 List<PlayerResponseModel> repsonseModel = new ArrayList<PlayerResponseModel>();
		 
		 if(players.size() > 0) {
			 for (Player player : players) {
				 repsonseModel.add(PlayerAdapter.getPlayerResponseModel(player));
			 }
			 
			 listResponse.data = repsonseModel;
			 listResponse.message = ResponseMessage.dataFetch;
			 listResponse.totalSize = repsonseModel.size();
		 }else {
			 listResponse.data = repsonseModel;
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
			Optional<Player> player =	playerRepository.findById(id);
			
			 if(player.isPresent()) {
				 singleResponse.data = PlayerAdapter.getPlayerResponseModel(player.get());
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
	public SingleResponse create(PlayerDTO playerDTO) {
		
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			if(playerDTO.getTeam() != null) {
				Optional<Team> team = teamRepository.findById(playerDTO.getTeam().getId());
				if(!team.isPresent()) {
					singleResponse.message = ResponseMessage.teamIdNotFound;
					return singleResponse;
				}
				playerDTO.setTeam(TeamAdapter.teamToTeamDto(team.get()));
			}
			
			if(playerDTO.getPlayerSkills() != null) {
				Optional<PlayerSkill> skill = playerSkillRepository.findById(playerDTO.getPlayerSkills().getId());
				if(!skill.isPresent()) {
					singleResponse.message = ResponseMessage.playerSkillNotFound;
					return singleResponse;
				}
				
				playerDTO.setPlayerSkills(PlayerSkillAdapter.daoToDto(skill.get()));
			}
			
			Player addedPlayer = playerRepository.save(PlayerAdapter.playerDtoToPlayer(playerDTO));
			singleResponse.message = ResponseMessage.added;
			singleResponse.data = PlayerAdapter.getPlayerResponseModel(addedPlayer);
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}

	@Override
	public SingleResponse update(long id, PlayerDTO playerDTO) {
		
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			
			Optional<Player> player =	playerRepository.findById(id);
			if(!player.isPresent()) {
				singleResponse.message = ResponseMessage.playerIdNotFound;
				return singleResponse;			
			}
			
			if(playerDTO.getTeam() != null) {
				Optional<Team> team = teamRepository.findById(playerDTO.getTeam().getId());
				if(!team.isPresent()) {
					singleResponse.message = ResponseMessage.teamIdNotFound;
					return singleResponse;
				}
				
				playerDTO.setTeam(TeamAdapter.teamToTeamDto(team.get()));
			}else {
				if(player.get().getTeam() != null) {
					playerDTO.setTeam(TeamAdapter.teamToTeamDto(player.get().getTeam()));
				}
			}
			
			if(playerDTO.getPlayerSkills() != null) {
				Optional<PlayerSkill> skill = playerSkillRepository.findById(playerDTO.getPlayerSkills().getId());
				if(!skill.isPresent()) {
					singleResponse.message = ResponseMessage.playerSkillNotFound;
					return singleResponse;
				}
				playerDTO.setPlayerSkills(PlayerSkillAdapter.daoToDto(skill.get()));
			}else {
				if(player.get().getPlayerSikll() != null) {
					playerDTO.setPlayerSkills(PlayerSkillAdapter.daoToDto(player.get().getPlayerSikll()));
				}
			}
			
			Player updatedPlayer = PlayerAdapter.playerDtoToPlayer(playerDTO);
			updatedPlayer.setId(id);
			playerRepository.save(updatedPlayer);
			singleResponse.data = PlayerAdapter.getPlayerResponseModel(updatedPlayer);;
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
			Optional<Player> player =	playerRepository.findById(id);
			
			 if(player.isPresent()) {
				 playerRepository.deleteById(id);
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
	public SingleResponse linkPlayerWithTeam(long player_id, long team_id) {
		
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			Optional<Player> player =	playerRepository.findById(player_id);
			
			if(!player.isPresent()) {
				singleResponse.message = ResponseMessage.playerIdNotFound;
				return singleResponse;
			}
			
			Optional<Team> team = teamRepository.findById(team_id);
			
			if(!team.isPresent()) {
				singleResponse.message = ResponseMessage.teamIdNotFound;
				return singleResponse;
			}
			
			player.get().setTeam(team.get());
			playerRepository.save(player.get());
			singleResponse.data = PlayerAdapter.getPlayerResponseModel(player.get());;
			singleResponse.message = ResponseMessage.playerLinkedWithTeam;
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}

}

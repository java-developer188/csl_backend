package com.bootcamp.csl_manager.adapter;

import com.bootcamp.csl_manager.dto.PlayerDTO;
import com.bootcamp.csl_manager.entity.Player;
import com.bootcamp.csl_manager.responsemodel.PlayerResponseModel;

public class PlayerAdapter {
	
	public static PlayerDTO playerToPlayerDto(Player player) {
		
		if(player == null) {
			return null;
		}
		
		PlayerDTO playerDTO = new PlayerDTO();
		
		playerDTO.setId(player.getId());
		playerDTO.setFirstName(player.getFirstName());
		playerDTO.setLastName(player.getLastName());
		playerDTO.setFatherName(player.getFatherName());
		playerDTO.setAge(player.getAge());
		playerDTO.setStatus(player.getStatus());
		playerDTO.setIsCaptain(player.getIsCaptain());
		playerDTO.setPlayerSkills(PlayerSkillAdapter.daoToDto(player.getPlayerSikll()));
		playerDTO.setTeam(TeamAdapter.teamToTeamDto(player.getTeam()));
		
		return playerDTO;
	}
	
	public static Player playerDtoToPlayer(PlayerDTO playerDTO) {
			
			if(playerDTO == null) {
				return null;
			}
			
			Player player = new Player();
			
			player.setId(playerDTO.getId());
			player.setFirstName(playerDTO.getFirstName());
			player.setLastName(playerDTO.getLastName());
			player.setFatherName(playerDTO.getFatherName());
			player.setAge(playerDTO.getAge());
			player.setStatus(playerDTO.getStatus());
			player.setIsCaptain(playerDTO.getIsCaptain());
			player.setPlayerSikll(PlayerSkillAdapter.dtoToDao(playerDTO.getPlayerSkills()));
			player.setTeam(TeamAdapter.teamDtoToTeam(playerDTO.getTeam()));
			
			return player;
		}
	
	public static PlayerResponseModel getPlayerResponseModel(Player player) {
		
		PlayerResponseModel playerResponseModel = new PlayerResponseModel();
		
		playerResponseModel.setId(player.getId());
		playerResponseModel.setFirstName(player.getFirstName());
		playerResponseModel.setLastName(player.getLastName());
		playerResponseModel.setFatherName(player.getFatherName());
		playerResponseModel.setAge(player.getAge());
		playerResponseModel.setStatus(player.getStatus());
		playerResponseModel.setIsCaptain(player.getIsCaptain());
		String skill = player.getPlayerSikll() != null ? player.getPlayerSikll().getSkill() : null;
		playerResponseModel.setSkill(skill);
		String teamName = player.getTeam() != null ? player.getTeam().getName() : null;
		playerResponseModel.setTeamName(teamName);
		
		return playerResponseModel;
	}

}

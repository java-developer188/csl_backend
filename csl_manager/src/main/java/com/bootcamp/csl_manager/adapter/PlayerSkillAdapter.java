package com.bootcamp.csl_manager.adapter;

import com.bootcamp.csl_manager.dto.PlayerSkillDTO;
import com.bootcamp.csl_manager.entity.PlayerSkill;

public class PlayerSkillAdapter {
	
	public static PlayerSkillDTO daoToDto(PlayerSkill playerSkill) {

		if (playerSkill == null) {
			return null;
		}

		PlayerSkillDTO playerSkillDTO = new PlayerSkillDTO();

		playerSkillDTO.setId(playerSkill.getId());
		playerSkillDTO.setSkill(playerSkill.getSkill());

		return playerSkillDTO;
	}

	public static PlayerSkill dtoToDao(PlayerSkillDTO playerSkillDTO) {

		if (playerSkillDTO == null) {
			return null;
		}

		PlayerSkill playerSkill = new PlayerSkill();
		
		playerSkill.setId(playerSkillDTO.getId());
		playerSkill.setSkill(playerSkillDTO.getSkill());
		return playerSkill;
	}

}

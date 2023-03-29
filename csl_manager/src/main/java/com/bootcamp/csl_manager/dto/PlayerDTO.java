package com.bootcamp.csl_manager.dto;


import com.bootcamp.csl_manager.enums.IsCaptain;
import com.bootcamp.csl_manager.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String fatherName;
	private int age;
	private Status status;
	private IsCaptain isCaptain;
	private PlayerSkillDTO playerSkills;
	private TeamDTO team;

}

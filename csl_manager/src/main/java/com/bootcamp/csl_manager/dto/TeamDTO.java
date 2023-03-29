package com.bootcamp.csl_manager.dto;

import com.bootcamp.csl_manager.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamDTO {
	
	private Long id;
	private String name;
	private String description;
	private Status status;
	private SportDTO sport;
}

package com.bootcamp.csl_manager.dto;

import com.bootcamp.csl_manager.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SportDTO {
	
	private Long id;
	private String name;
	private String description;
	private Status status;
	private TournamentDTO tournament;
}

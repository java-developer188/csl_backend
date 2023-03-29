package com.bootcamp.csl_manager.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.bootcamp.csl_manager.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentDTO {
	
	private Long id;
	private String name;
	private String description;
	@Enumerated(EnumType.STRING)
	private Status status;
}

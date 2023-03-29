package com.bootcamp.csl_manager.responsemodel;

import com.bootcamp.csl_manager.enums.IsWinner;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FixtureTeamResponseModel {
	
	private Long id;
	private String name;
	private String description;
	private IsWinner isWinner = IsWinner.FALSE;
	private int matchScore = 0;

}

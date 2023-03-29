package com.bootcamp.csl_manager.dto;

import com.bootcamp.csl_manager.enums.IsWinner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamMatchScore {
	int matchScore = 0;
	IsWinner isWinner = IsWinner.FALSE;
}

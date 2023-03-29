package com.bootcamp.csl_manager.dto;

import java.time.LocalDateTime;

import com.bootcamp.csl_manager.enums.FixtureAlgorithms;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateFixtureDTO {
	
	private long sportId;
	private int noOfTeams;
	private int noOfGroups;
	private int noOfMatchPerDay;
	private int matchDuration;
	private int breakBetweenMatches;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime matchStartDate;
	private FixtureAlgorithms fixtureAlgorithms = FixtureAlgorithms.ROUND_ROBIN_WITHOUT_GROUP;

}

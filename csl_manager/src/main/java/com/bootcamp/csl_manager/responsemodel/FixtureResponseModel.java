package com.bootcamp.csl_manager.responsemodel;

import java.util.ArrayList;
import java.util.List;

import com.bootcamp.csl_manager.dto.MatchDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FixtureResponseModel {
	
	private MatchDTO match;
	private List<FixtureTeamResponseModel> teams = new ArrayList<>();
}

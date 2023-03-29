package com.bootcamp.csl_manager.responsemodel;

import java.util.ArrayList;
import java.util.List;

import com.bootcamp.csl_manager.enums.IsCaptain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerScoreResponseModel {
	
	private String name;
	private int age;
	private String skill;
	private IsCaptain isCaptain;
	private int totalGoal;
	private int totalMatch;
	private int totalOwnGoal;
	private double goalAverage;
	private List<PlayerMatchScoreResponseModel> matches = new ArrayList<>();

}
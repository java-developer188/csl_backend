package com.bootcamp.csl_manager.responsemodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointsTableResponseModel {
	
	private String name;
	private int played;
	private int won;
	private int points;
	private int score;

}

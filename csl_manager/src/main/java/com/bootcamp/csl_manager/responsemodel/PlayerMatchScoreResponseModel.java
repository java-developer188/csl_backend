package com.bootcamp.csl_manager.responsemodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerMatchScoreResponseModel {
	private int matchNo;
	private int matchGoal;
	private int matchOwnGoal;
	private String matchDiscription;
}

package com.bootcamp.csl_manager.responsemodel;

import com.bootcamp.csl_manager.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamResponseModel {
	private Long id;
	private String name;
	private String description;
	private Status status;
	private String sport;
}

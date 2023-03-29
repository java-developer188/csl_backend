package com.bootcamp.csl_manager.responsemodel;

import com.bootcamp.csl_manager.enums.IsCaptain;
import com.bootcamp.csl_manager.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerResponseModel {
	private Long id;
	private String firstName;
	private String lastName;
	private String fatherName;
	private int age;
	private Status status;
	private IsCaptain isCaptain;
	private String skill;
	private String teamName;
}

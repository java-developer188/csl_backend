package com.bootcamp.csl_manager.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bootcamp.csl_manager.enums.IsCaptain;
import com.bootcamp.csl_manager.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_player")
@Setter
@Getter
public class Player {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "father_name")
	private String fatherName;
	@Column(name = "age")
	private int age;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status = Status.ACTIVE;
	@Column(name = "is_captain")
	@Enumerated(EnumType.STRING)
	private IsCaptain isCaptain = IsCaptain.FALSE;
	
	@ManyToOne
	@JoinColumn(name = "skill_id")
	PlayerSkill playerSikll;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	Team team;
	
	@ManyToMany
	@JoinTable(name = "tbl_player_match",
				joinColumns = @JoinColumn(name = "player_id"),
				inverseJoinColumns = @JoinColumn(name = "match_id"))
	private Set<Match> matches = new HashSet<>();
	
	public Player(){ }
}

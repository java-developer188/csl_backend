package com.bootcamp.csl_manager.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bootcamp.csl_manager.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_team")
@Setter
@Getter
public class Team {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "sport_id")
	Sport sport;
	
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private Set<Player> players = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "tbl_team_match",
				joinColumns = @JoinColumn(name = "team_id"),
				inverseJoinColumns = @JoinColumn(name = "match_id"))
	private Set<Match> matches = new HashSet<>();
}

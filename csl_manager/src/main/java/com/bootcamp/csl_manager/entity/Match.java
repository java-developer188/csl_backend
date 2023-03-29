package com.bootcamp.csl_manager.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.bootcamp.csl_manager.enums.Groups;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_match")
@Setter
@Getter
public class Match {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "match_number")
	private int matchNumber;
	@Column(name = "start_date_time")
	private LocalDateTime  startDateTime;
	@Column(name = "end_date_time")
	private LocalDateTime endDateTime;
	@Column(name = "venue")
	private String venue;
	@Column(name = "description")
	private String description;
	@ManyToMany(mappedBy = "matches")
	private Set<Player> players = new HashSet<>();
	@ManyToMany(mappedBy = "matches")
	private Set<Team> teams = new HashSet<>();
	@Column(name = "group_name")
	@Enumerated(EnumType.STRING)
	private Groups groupName = Groups.NONE;
}

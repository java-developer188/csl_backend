package com.bootcamp.csl_manager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bootcamp.csl_manager.enums.IsWinner;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_team_match")
@Setter
@Getter
public class TeamMatch {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	@ManyToOne
	@JoinColumn(name = "match_id")
	private Match match;
	private int matchScore = 0;
	@Column(name = "is_winner")
	@Enumerated(EnumType.STRING)
	private IsWinner isWinner = IsWinner.FALSE;
}

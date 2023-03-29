package com.bootcamp.csl_manager.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_player_match")
@Setter
@Getter
public class PlayerMatch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;
	@ManyToOne
	@JoinColumn(name = "match_id")
	private Match match;
	private int score = 0;
	private int ownGoal = 0;
}

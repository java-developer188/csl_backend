package com.bootcamp.csl_manager.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_player_skill")
@Setter
@Getter
public class PlayerSkill {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "skill")
	private String skill;
	@OneToMany(mappedBy = "playerSikll", cascade = CascadeType.ALL)
	private Set<Player> players;
}

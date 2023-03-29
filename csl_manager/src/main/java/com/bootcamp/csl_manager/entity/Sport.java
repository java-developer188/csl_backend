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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bootcamp.csl_manager.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_sport")
@Setter
@Getter
public class Sport {
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
	@JoinColumn(name = "tournament_id")
	Tournament tournament;
	@OneToMany(mappedBy = "sport", cascade = CascadeType.ALL)
	private Set<Team> teams = new HashSet<>();;
}

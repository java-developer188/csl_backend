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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bootcamp.csl_manager.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_tournament")
@Setter
@Getter
public class Tournament {
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
	@OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
	private Set<Sport> sports = new HashSet<>();
}

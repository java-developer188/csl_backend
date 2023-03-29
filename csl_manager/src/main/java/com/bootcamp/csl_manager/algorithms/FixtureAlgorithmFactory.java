package com.bootcamp.csl_manager.algorithms;

import org.springframework.beans.factory.annotation.Autowired;

import com.bootcamp.csl_manager.enums.FixtureAlgorithms;

public class FixtureAlgorithmFactory {
	
	@Autowired
	RoundRobinWithGroup roundRobinWithGroup;
	@Autowired
	RoundRobinWithoutGroup roundRobinWithoutGroup;
	
	public IFixtureAlgorithms getAlogrithms(FixtureAlgorithms algo) {
		
		if(FixtureAlgorithms.ROUND_ROBIN_WITHOUT_GROUP == algo) {
			return roundRobinWithoutGroup;
		}else if(FixtureAlgorithms.ROUND_ROBIN_WITH_GROUP == algo) {
			return roundRobinWithGroup;
		}
		
		return null;
	}

}

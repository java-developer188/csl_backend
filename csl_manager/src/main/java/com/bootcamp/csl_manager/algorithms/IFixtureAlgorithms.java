package com.bootcamp.csl_manager.algorithms;

import com.bootcamp.csl_manager.dto.GenerateFixtureDTO;
import com.bootcamp.csl_manager.utilities.SingleResponse;

public interface IFixtureAlgorithms {
	public SingleResponse algo(GenerateFixtureDTO generateFixtureDTO);
}

package com.bootcamp.csl_manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.csl_manager.adapter.MatchAdapter;
import com.bootcamp.csl_manager.dto.MatchDTO;
import com.bootcamp.csl_manager.entity.Match;
import com.bootcamp.csl_manager.repository.MatchRepository;
import com.bootcamp.csl_manager.utilities.ListResponse;
import com.bootcamp.csl_manager.utilities.ResponseMessage;
import com.bootcamp.csl_manager.utilities.SingleResponse;

@Service
public class MatchServiceImp implements MatchService {

	@Autowired
	MatchRepository matchRepository;
	
	@Override
	public ListResponse findAll() {
		ListResponse listResponse = new ListResponse();
		listResponse.isError = true;
		try {
		 List<Match> matches =	matchRepository.findAll();
		 List<MatchDTO> matchesDTO = new ArrayList<MatchDTO>();
		 
		 if(matches.size() > 0) {
			 for (Match match : matches) {
				 matchesDTO.add(MatchAdapter.matchToMatchDto(match));
			 }
			 
			 listResponse.data = matchesDTO;
			 listResponse.message = ResponseMessage.dataFetch;
			 listResponse.totalSize = matchesDTO.size();
		 }else {
			 listResponse.data = matchesDTO;
			 listResponse.message = ResponseMessage.dataNotFound;
			 return listResponse;
		 }
		 
		 listResponse.isError = false;
		}catch(Exception ex) {
			listResponse.isError = true;
			listResponse.message = "System Level Error";
			throw ex;
		}
		return listResponse;
	}
	
	@Override
	public SingleResponse findById(long id) {
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;
		
		try {
			Optional<Match> match =	matchRepository.findById(id);
			
			 if(match.isPresent()) {
				 singleResponse.data = MatchAdapter.matchToMatchDto(match.get());
				 singleResponse.message = ResponseMessage.dataFetch;
			 }else {
				 singleResponse.data = null;
				 singleResponse.message = ResponseMessage.dataNotFound;
				 return singleResponse;
			 }
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error";
			throw ex;
		}
		return singleResponse;
	}

	@Override
	public SingleResponse create(MatchDTO matchDTO) {
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			matchRepository.save(MatchAdapter.matchDtoToMatch(matchDTO));
			singleResponse.message = ResponseMessage.added;
			singleResponse.data = matchDTO;
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}

	@Override
	public SingleResponse update(long id, MatchDTO matchDTO) {
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			Optional<Match> match =	matchRepository.findById(id);
			if(match.isPresent()) {
				Match updatedMatch = MatchAdapter.matchDtoToMatch(matchDTO);
				updatedMatch.setId(id);
				matchRepository.save(updatedMatch);
				singleResponse.data = matchDTO;
				singleResponse.message = ResponseMessage.updated;	
			}else {
				singleResponse.message = ResponseMessage.dataNotFound;	
				return singleResponse;
			}
			
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}

	@Override
	public SingleResponse deleteById(long id) {
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;
		try {
			Optional<Match> match =	matchRepository.findById(id);
			
			 if(match.isPresent()) {
				 matchRepository.deleteById(id);
				 singleResponse.message = ResponseMessage.deleted;
			 }else {
				 singleResponse.data = null;
				 singleResponse.message = ResponseMessage.dataNotFound;
				 return singleResponse;
			 }
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error";
			throw ex;
		}
		return singleResponse;
	}

}

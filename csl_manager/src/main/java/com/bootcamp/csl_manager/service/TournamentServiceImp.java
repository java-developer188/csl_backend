package com.bootcamp.csl_manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.csl_manager.adapter.TournamentAdapter;
import com.bootcamp.csl_manager.dto.TournamentDTO;
import com.bootcamp.csl_manager.entity.Tournament;
import com.bootcamp.csl_manager.repository.TournamentRepository;
import com.bootcamp.csl_manager.utilities.ListResponse;
import com.bootcamp.csl_manager.utilities.ResponseMessage;
import com.bootcamp.csl_manager.utilities.SingleResponse;

@Service
public class TournamentServiceImp implements TournamentService  {
	
	@Autowired
	TournamentRepository tournamentRepository;
	
	@Override
	public ListResponse findAll() {
		ListResponse listResponse = new ListResponse();
		listResponse.isError = true;
		try {
		 List<Tournament> tournaments =	tournamentRepository.findAll();
		 List<TournamentDTO> tournamentsDTO = new ArrayList<TournamentDTO>();
		 
		 if(tournaments.size() > 0) {
			 for (Tournament player : tournaments) {
				 tournamentsDTO.add(TournamentAdapter.tournamentToTournamentDto(player));
			 }
			 
			 listResponse.data = tournamentsDTO;
			 listResponse.message = ResponseMessage.dataFetch;
			 listResponse.totalSize = tournamentsDTO.size();
		 }else {
			 listResponse.data = tournamentsDTO;
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
			Optional<Tournament> tournament =	tournamentRepository.findById(id);
			
			 if(tournament.isPresent()) {
				 singleResponse.data = TournamentAdapter.tournamentToTournamentDto(tournament.get());
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
	public SingleResponse create(TournamentDTO tournamentDTO) {
		
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			tournamentRepository.save(TournamentAdapter.tournamentDtoToTournament(tournamentDTO));
			singleResponse.message = ResponseMessage.added;
			singleResponse.data = tournamentDTO;
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}

	@Override
	public SingleResponse update(long id, TournamentDTO tournamentDTO) {
		
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			Optional<Tournament> tournament =	tournamentRepository.findById(id);
			if(tournament.isPresent()) {
				Tournament updatedTournament = TournamentAdapter.tournamentDtoToTournament(tournamentDTO);
				updatedTournament.setId(id);
				tournamentRepository.save(updatedTournament);
				singleResponse.data = tournamentDTO;
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
			Optional<Tournament> tournament =	tournamentRepository.findById(id);
			
			 if(tournament.isPresent()) {
				 tournamentRepository.deleteById(id);
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

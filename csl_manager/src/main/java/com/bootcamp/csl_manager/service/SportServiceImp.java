package com.bootcamp.csl_manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.csl_manager.adapter.SportAdapter;
import com.bootcamp.csl_manager.adapter.TournamentAdapter;
import com.bootcamp.csl_manager.dto.SportDTO;
import com.bootcamp.csl_manager.entity.Sport;
import com.bootcamp.csl_manager.entity.Tournament;
import com.bootcamp.csl_manager.repository.SportRepository;
import com.bootcamp.csl_manager.repository.TournamentRepository;
import com.bootcamp.csl_manager.utilities.ListResponse;
import com.bootcamp.csl_manager.utilities.ResponseMessage;
import com.bootcamp.csl_manager.utilities.SingleResponse;

@Service
public class SportServiceImp implements SportService {
	
	@Autowired
	SportRepository sportRepository;
	@Autowired
	TournamentRepository tournamentRepository;

	@Override
	public ListResponse findAll() {
		ListResponse listResponse = new ListResponse();
		listResponse.isError = true;
		try {
			List<Sport> sport = sportRepository.findAll();
			List<SportDTO> sportDTO = new ArrayList<SportDTO>();

			if (sport.size() > 0) {
				for (Sport singularSport : sport) {
					sportDTO.add(SportAdapter.sportToSportDto(singularSport));
				}

				listResponse.data = sportDTO;
				listResponse.message = ResponseMessage.dataFetch;
				listResponse.totalSize = sportDTO.size();
			} else {
				listResponse.data = sportDTO;
				listResponse.message = ResponseMessage.dataNotFound;
			}

			listResponse.isError = false;
		} catch (Exception ex) {
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
			Optional<Sport> sport = sportRepository.findById(id);

			if (sport.isPresent()) {
				singleResponse.data = SportAdapter.sportToSportDto(sport.get());
				singleResponse.message = ResponseMessage.dataFetch;
			} else {
				singleResponse.data = null;
				singleResponse.message = ResponseMessage.dataNotFound;
			}
			singleResponse.isError = false;
		} catch (Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error";
			throw ex;
		}
		return singleResponse;
	}

	@Override
	public SingleResponse create(SportDTO sportDTO) {

		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			
			if(sportDTO.getTournament() != null) {
				Optional<Tournament> tournament = tournamentRepository.findById(sportDTO.getTournament().getId());
				if(!tournament.isPresent()) {
					singleResponse.message = ResponseMessage.tournamentIdNotFound;
					return singleResponse;
				}
				sportDTO.setTournament(TournamentAdapter.tournamentToTournamentDto(tournament.get()));
			}
			
			sportRepository.save(SportAdapter.sportDtoToSport(sportDTO));
			singleResponse.message = ResponseMessage.added;
			singleResponse.data = sportDTO;
			singleResponse.isError = false;
		} catch (Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}

	@Override
	public SingleResponse update(long id, SportDTO sportDTO) {

		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			Optional<Sport> sport = sportRepository.findById(id);
			
			if (!sport.isPresent()) {
				singleResponse.message = ResponseMessage.sportIdNotFound;
				return singleResponse;
			}
			
			if(sportDTO.getTournament() != null) {
				Optional<Tournament> tournament = tournamentRepository.findById(sportDTO.getTournament().getId());
				if(!tournament.isPresent()) {
					singleResponse.message = ResponseMessage.tournamentIdNotFound;
					return singleResponse;
				}
				sportDTO.setTournament(TournamentAdapter.tournamentToTournamentDto(tournament.get()));
			}else
			{
				if(sport.get().getTournament() !=null) {
					sportDTO.setTournament(TournamentAdapter.tournamentToTournamentDto(sport.get().getTournament()));
				}
			}
			
			Sport updatedSport = SportAdapter.sportDtoToSport(sportDTO);
			updatedSport.setId(id);
			sportRepository.save(updatedSport);
			singleResponse.data = sportDTO;
			singleResponse.message = ResponseMessage.updated;

			singleResponse.isError = false;
		} catch (Exception ex) {
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
			Optional<Sport> player = sportRepository.findById(id);

			if (player.isPresent()) {
				sportRepository.deleteById(id);
				singleResponse.message = ResponseMessage.deleted;
			} else {
				singleResponse.data = null;
				singleResponse.message = ResponseMessage.dataNotFound;
			}
			singleResponse.isError = false;
		} catch (Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error";
			throw ex;
		}
		return singleResponse;
	}
	
	@Override
	public SingleResponse linkSportWithTournament(long sport_id, long tournament_id) {
		
		SingleResponse singleResponse = new SingleResponse();
		singleResponse.isError = true;

		try {
			
			Optional<Sport> sport = sportRepository.findById(sport_id);

			if (!sport.isPresent()) {
				singleResponse.message = ResponseMessage.sportIdNotFound;
				return singleResponse;
			}
			
			Optional<Tournament> tournament =	tournamentRepository.findById(tournament_id);
			
			if(!tournament.isPresent()) {
				singleResponse.message = ResponseMessage.tournamentIdNotFound;
				return singleResponse;
			}
			
			sport.get().setTournament(tournament.get());
			sportRepository.save(sport.get());
			singleResponse.data = SportAdapter.sportToSportDto(sport.get());
			singleResponse.message = ResponseMessage.sportLinkedWithTournament;
			singleResponse.isError = false;
		}catch(Exception ex) {
			singleResponse.isError = true;
			singleResponse.message = "System Level Error :: " + ex.getMessage();
			throw ex;
		}
		return singleResponse;
	}
}

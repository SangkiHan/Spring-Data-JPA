package com.spring.data.jpa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spring.data.jpa.dto.TeamDto;
import com.spring.data.jpa.entity.Team;
import com.spring.data.jpa.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamService {
	
	private final TeamRepository teamRepository;
	
	public void save(TeamDto teamDto) {
		Team team = new Team(teamDto.getId(), teamDto.getName());
		teamRepository.save(team);
	}
	
	public TeamDto selectOne(Long id) {
		Team team = teamRepository.findById(id).get();
		TeamDto teamDto = new TeamDto(team);
		return teamDto;
	}
	
	public List<TeamDto> selectList() {
		List<Team> teams = teamRepository.findAll();
		List<TeamDto> teamDto = teams.stream()
				.map(o -> new TeamDto(o))
				.collect(Collectors.toList());
		
		return teamDto;
	}

}

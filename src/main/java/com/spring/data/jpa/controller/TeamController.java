package com.spring.data.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.data.jpa.dto.TeamDto;
import com.spring.data.jpa.service.TeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/team")
public class TeamController {
	
	private final TeamService teamService;
	
	@PostMapping("/save")
	public void save(@RequestBody TeamDto teamDto) {
		teamService.save(teamDto);
	}
	
	@GetMapping("/selectOne")
	public TeamDto selectOne(@RequestParam("teamId") Long id) {
		return teamService.selectOne(id);
	}
	
	@GetMapping("/selectList")
	public List<TeamDto> selectList() {
		return teamService.selectList();
	}

}

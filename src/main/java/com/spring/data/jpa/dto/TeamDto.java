package com.spring.data.jpa.dto;

import com.spring.data.jpa.entity.Team;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TeamDto {
	private Long id;
	private String name;
	
	public TeamDto(Team team) {
		this.id = team.getId();
		this.name = team.getName();
	}
}

package com.spring.data.jpa.dto;

import com.spring.data.jpa.entity.Member;

import lombok.Data;

public class MemberDto {
	
	@Data
	public static class Request{
		private Long id;
		private Long teamId;
		private String username;
		private int age;
	}
	
	@Data
	public static class Info{
		private Long id;
		private String username;
		private int age;
		private TeamDto teamDto;
		
		public Info(Member member) {
			this.id = member.getId();
			this.username = member.getUsername();
			this.age = member.getAge();
			this.teamDto = new TeamDto(member.getTeam());
		}
	}
}

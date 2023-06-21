package com.spring.data.jpa.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.spring.data.jpa.dto.MemberDto;
import com.spring.data.jpa.entity.Member;
import com.spring.data.jpa.entity.Team;
import com.spring.data.jpa.repository.MemberRepository;
import com.spring.data.jpa.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final TeamRepository teamRepository;
	
	public void save(MemberDto.Request memberDto) {
		Member member = new Member(memberDto.getId(), memberDto.getUsername(), memberDto.getAge());
		Team team = teamRepository.findById(memberDto.getTeamId()).get();
		member.changeTeam(team);
		memberRepository.save(member);
	}
	
	public MemberDto.Info selectOne(Long id){
		Member member = memberRepository.findById(id).get();
		MemberDto.Info info = new MemberDto.Info(member);
		return info;
	}
	
	public List<MemberDto.Info> selectList(){
		List<Member> member = memberRepository.findAll();
		List<MemberDto.Info> infos = member.stream()
				.map(o -> new MemberDto.Info(o))
				.collect(Collectors.toList());
		return infos;
	}
}

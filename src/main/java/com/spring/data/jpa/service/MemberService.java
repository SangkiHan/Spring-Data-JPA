package com.spring.data.jpa.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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
	
	/*
	 * Paging 예제
	 * */
	public List<MemberDto.Info> selectListByPaging(){
		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC));
		
		Page<Member> member = memberRepository.findAllByPaging(pageRequest);
		List<Member> members = member.getContent();
		
		int memberSize = members.size(); //members 사이즈
		Long elementsTotalCnt = member.getTotalElements(); //member의 total개수
		int pageNumber = member.getNumber(); //현재 페이지 넘버
		int pageTotalCnt = member.getTotalPages(); //페이지 전체개수
		
		List<MemberDto.Info> infos = members.stream()
				.map(o -> new MemberDto.Info(o))
				.collect(Collectors.toList());
		return infos;
	}
	
	/*
	 * Slice 예제
	 * Slice는 앱에서 더보기같은 기능을 개발할 때 사용하는 기능으로 다음페이지가 있는지 없는 지 limit를 +1하여 데이터 한개를 더 조회하는 식으로 다음페이지여부를 판단한다.
	 * */
	public List<MemberDto.Info> selectListBySlice(){
		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC));
		
		Slice<Member> member = memberRepository.findAllByPaging(pageRequest);
		List<Member> members = member.getContent();
		
		int memberSize = members.size(); //members 사이즈
		int pageNumber = member.getNumber(); //현재 페이지 넘버
		
		List<MemberDto.Info> infos = members.stream()
				.map(o -> new MemberDto.Info(o))
				.collect(Collectors.toList());
		return infos;
	}
}

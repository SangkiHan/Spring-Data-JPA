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
import com.spring.data.jpa.dto.PageDto;
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
	 * Paging ����
	 * */
	@SuppressWarnings("unused")
	public List<MemberDto.Info> selectListByPaging(PageDto pageDto){
		PageRequest pageRequest = PageRequest.of(pageDto.getOffset(), pageDto.getLimit(), Sort.by(Sort.Direction.DESC, "id"));
		
		Page<Member> page = memberRepository.findAllPaging(pageRequest);
		
		Page<MemberDto.Info> infos = page.map(member -> new MemberDto.Info(member));
		
		List<MemberDto.Info> members = infos.getContent();
		
		int memberSize = members.size(); //members ������
		Long elementsTotalCnt = page.getTotalElements(); //member�� total����
		int pageNumber = page.getNumber(); //���� ������ �ѹ�
		int pageTotalCnt = page.getTotalPages(); //������ ��ü����
		
		return members;
	}
	
	/*
	 * Slice ����
	 * Slice�� �ۿ��� �����ⰰ�� ����� ������ �� ����ϴ� ������� ������������ �ִ��� ���� �� limit�� +1�Ͽ� ������ �Ѱ��� �� ��ȸ�ϴ� ������ �������������θ� �Ǵ��Ѵ�.
	 * */
	@SuppressWarnings("unused")
	public List<MemberDto.Info> selectListBySlice(PageDto pageDto){
		PageRequest pageRequest = PageRequest.of(pageDto.getOffset(), pageDto.getLimit(), Sort.by(Sort.Direction.DESC));
		
		Slice<Member> slice = memberRepository.findAllPaging(pageRequest);
		Slice<MemberDto.Info> infos = slice.map(member -> new MemberDto.Info(member));
		
		List<MemberDto.Info> members = infos.getContent();
		
		int memberSize = members.size(); //members ������
		int pageNumber = slice.getNumber(); //���� ������ �ѹ�
		
		return members;
	}
	
	public List<MemberDto.Info> selectListByFetch(){
		List<Member> member = memberRepository.findAll();
		List<MemberDto.Info> infos = member.stream()
				.map(o -> new MemberDto.Info(o))
				.collect(Collectors.toList());
		return infos;
	}
	
	
	public int updateMember(MemberDto.Request info) {
		return memberRepository.updateMember(info.getId(), info.getUsername());
	}
}

package com.spring.data.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.data.jpa.dto.MemberDto;
import com.spring.data.jpa.dto.PageDto;
import com.spring.data.jpa.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
	
	private final MemberService memberService;
	
	@PostMapping("/save")
	public void save(@RequestBody MemberDto.Request memberDto) {
		memberService.save(memberDto);
	}
	
	@GetMapping("/v1/selectOne")
	public MemberDto.Info selectOneV1(@RequestParam("memberId") Long memberId){
		return memberService.selectOne(memberId);
	}
	
	@GetMapping("/v1/selectList")
	public List<MemberDto.Info> selectListV1(){
		return memberService.selectList();
	}
	
	@GetMapping("/v2/selectList")
	public List<MemberDto.Info> selectListV2(@RequestBody PageDto pageDto){
		return memberService.selectListByPaging(pageDto);
	}
	
	@GetMapping("/v3/selectList")
	public List<MemberDto.Info> selectListV3(){
		return memberService.selectListByFetch();
	}
	
	@PostMapping("/update")
	public void update(@RequestBody MemberDto.Request info) {
		memberService.updateMember(info);
	}
}


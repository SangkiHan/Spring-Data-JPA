package com.spring.data.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.data.jpa.dto.MemberDto;
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
	
	@GetMapping("/selectOne")
	public MemberDto.Info selectOne(@RequestParam("memberId") Long memberId){
		return memberService.selectOne(memberId);
	}
	
	@GetMapping("/selectList")
	public List<MemberDto.Info> selectList(){
		return memberService.selectList();
	}
}

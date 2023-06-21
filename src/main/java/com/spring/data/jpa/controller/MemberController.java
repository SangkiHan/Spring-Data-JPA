package com.spring.data.jpa.controller;

import org.springframework.web.bind.annotation.RestController;

import com.spring.data.jpa.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;

}

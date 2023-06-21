package com.spring.data.jpa.service;

import org.springframework.stereotype.Service;

import com.spring.data.jpa.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	

}

package com.spring.data.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.data.jpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}

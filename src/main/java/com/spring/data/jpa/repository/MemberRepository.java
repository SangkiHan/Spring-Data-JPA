package com.spring.data.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.data.jpa.dto.MemberDto;
import com.spring.data.jpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
	/*
	 * 순수 JPA보다 @Query를 사용하면 was구동시점에 문법오류가 있는지 체크할 수 있는 장점이 있다.
	 * */
	@Query("select m from Member m where m.username = :username and m.age > :age")
	List<Member> findUser(@Param("username") String username, @Param("age") int age);
	
	@Query("select m from Member m where username in :usernames")
	List<Member> findByNames(@Param("usernames") List<String> usernames);
	
	//	Entity가 아닌 데이터 조회시
	@Query("select m.username from Member m")
	List<String> findUsernameList();
	
	@Query("select new com.spring.data.jpa.dto.MemberDto.Info(m.id, m.username, t.name) from Member m join m.team t")
	List<MemberDto.Info> findMemberDtoList();
	
	// 응답 파라미터 타입 예제
	List<Member> findListByUsername(String username);//컬렉션
	Member findMemberByUsername(String username);//단건
	Optional<Member> findOptionalByUsername(String username);//단건 optional
	
	//페이징예제
	Page<Member> findAllByPaging(Pageable pageable);
	
	//Slice예제
	Slice<Member> findAllBySlice(Pageable pageable);
	
}

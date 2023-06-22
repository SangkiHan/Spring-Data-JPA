package com.spring.data.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	// 응답 파라미터 타입 예제
	List<Member> findListByUsername(String username);//컬렉션
	Member findMemberByUsername(String username);//단건
	Optional<Member> findOptionalByUsername(String username);//단건 optional
	
	//페이징예제
	@Query("select m from Member m")
	Page<Member> findAllPaging(Pageable pageable);
	
	//Slice예제
	@Query("select m from Member m")
	Slice<Member> findAllSlice(Pageable pageable);
	
	// !!!주의 벌크성 수정은 영속성컨텍스트에 반영이 안되기 때문에 update 후 바로 조회를 하려면 flush로 DB에 반영을 해주고 clear로 영속성컨텍스트 초기화를 해줘야한다. 
	@Modifying
	@Query("update Member m set m.username = :username where member_id = :id")
	public int updateMember(@Param("id") Long id, @Param("username") String username);
	
//	@Query("select m from Member m left join fetch m.team t")
	@Override
	@EntityGraph(attributePaths = "team")
	public List<Member> findAll();
	
}

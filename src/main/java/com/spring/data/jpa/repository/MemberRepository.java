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
	 * ���� JPA���� @Query�� ����ϸ� was���������� ���������� �ִ��� üũ�� �� �ִ� ������ �ִ�.
	 * */
	@Query("select m from Member m where m.username = :username and m.age > :age")
	List<Member> findUser(@Param("username") String username, @Param("age") int age);
	
	@Query("select m from Member m where username in :usernames")
	List<Member> findByNames(@Param("usernames") List<String> usernames);
	
	//	Entity�� �ƴ� ������ ��ȸ��
	@Query("select m.username from Member m")
	List<String> findUsernameList();
	
	@Query("select new com.spring.data.jpa.dto.MemberDto.Info(m.id, m.username, t.name) from Member m join m.team t")
	List<MemberDto.Info> findMemberDtoList();
	
	// ���� �Ķ���� Ÿ�� ����
	List<Member> findListByUsername(String username);//�÷���
	Member findMemberByUsername(String username);//�ܰ�
	Optional<Member> findOptionalByUsername(String username);//�ܰ� optional
	
	//����¡����
	Page<Member> findAllByPaging(Pageable pageable);
	
	//Slice����
	Slice<Member> findAllBySlice(Pageable pageable);
	
}

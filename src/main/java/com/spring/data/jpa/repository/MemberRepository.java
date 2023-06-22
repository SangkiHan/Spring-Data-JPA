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
	 * ���� JPA���� @Query�� ����ϸ� was���������� ���������� �ִ��� üũ�� �� �ִ� ������ �ִ�.
	 * */
	@Query("select m from Member m where m.username = :username and m.age > :age")
	List<Member> findUser(@Param("username") String username, @Param("age") int age);
	
	@Query("select m from Member m where username in :usernames")
	List<Member> findByNames(@Param("usernames") List<String> usernames);
	
	//	Entity�� �ƴ� ������ ��ȸ��
	@Query("select m.username from Member m")
	List<String> findUsernameList();
	
	// ���� �Ķ���� Ÿ�� ����
	List<Member> findListByUsername(String username);//�÷���
	Member findMemberByUsername(String username);//�ܰ�
	Optional<Member> findOptionalByUsername(String username);//�ܰ� optional
	
	//����¡����
	@Query("select m from Member m")
	Page<Member> findAllPaging(Pageable pageable);
	
	//Slice����
	@Query("select m from Member m")
	Slice<Member> findAllSlice(Pageable pageable);
	
	// !!!���� ��ũ�� ������ ���Ӽ����ؽ�Ʈ�� �ݿ��� �ȵǱ� ������ update �� �ٷ� ��ȸ�� �Ϸ��� flush�� DB�� �ݿ��� ���ְ� clear�� ���Ӽ����ؽ�Ʈ �ʱ�ȭ�� ������Ѵ�. 
	@Modifying
	@Query("update Member m set m.username = :username where member_id = :id")
	public int updateMember(@Param("id") Long id, @Param("username") String username);
	
//	@Query("select m from Member m left join fetch m.team t")
	@Override
	@EntityGraph(attributePaths = "team")
	public List<Member> findAll();
	
}

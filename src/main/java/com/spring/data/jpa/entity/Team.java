package com.spring.data.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of= {"id","name"})
public class Team {
	@Id @GeneratedValue
	@Column(name = "team_id")
	private Long id;
	private String name;
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<>();
	
	public Team(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}

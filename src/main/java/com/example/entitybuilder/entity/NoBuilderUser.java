package com.example.entitybuilder.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Getter/Setter 를 사용하는 Entity
 * */
@Entity(name = "test_user_no_builder")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class NoBuilderUser {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 필수 컬럼
	@Column(nullable = false, length = 10)
	private String name;
	@Column(nullable = false, length = 50)
	private String email;
	@CreatedDate @Column(nullable = false, updatable = false)
	private LocalDateTime regDate;
	@Builder.Default @LastModifiedDate @Column(nullable = false) 
	private LocalDateTime upDate = LocalDateTime.now();
	
	// 옵션 컬럼
	@Column private int age;
	@Column(length = 50)
	private String etc;
	
}

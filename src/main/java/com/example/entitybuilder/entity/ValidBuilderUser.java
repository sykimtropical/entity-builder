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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Builder 패턴의 필수값 이용
 * */
@Entity(name = "test_user_valid")
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ValidBuilderUser {
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


	public static ValidBuilderUserBuilder builder(
		String name,
		String email
	) {
		return new ValidBuilderUserBuilder()
			.name(name)
			.email(email);
	}

}

package com.example.entitybuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.example.entitybuilder.entity.NoBuilderUser;
import com.example.entitybuilder.entity.OnlyBuilderUser;
import com.example.entitybuilder.entity.ValidBuilderUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@Import(EntityBuilderApplication.class) // @EnableJpaAuditing 활성화를 위해 어노테이션이 존재하는 class Import
class EntityBuilderApplicationTests {
	@PersistenceContext
	private EntityManager em;

	@Test
	@DisplayName("Getter/Setter만 이용한 Entity")
	void createUserGetterSetter() {
		NoBuilderUser user = new NoBuilderUser();
		user.setName("test1");
		assertAll(
			() -> assertDoesNotThrow(() -> em.persist(user))
		);
	}

	@Test
	@DisplayName("Builder만 이용한 Entity")
	void createUserOnlyBuilder() {
		// 컴파일 오류
		// OnlyBuilderUser user1 = new OnlyBuilderUser();
		// OnlyBuilderUser user2 = new OnlyBuilderUser(1L, "test", "test@test.com", LocalDateTime.now(), LocalDateTime.now(), 10, "etc");

		OnlyBuilderUser user = OnlyBuilderUser.builder().build();
		assertDoesNotThrow(() -> em.persist(user));

	}


	@Test
	@DisplayName("필수값 제한 Builder Entity")
	void createUserValidBuilder() {
		// 컴파일 오류
		// ValidBuilderUser user1 = new ValidBuilderUser();
		// ValidBuilderUser user2 = new ValidBuilderUser(1L, "test", "test@test.com", LocalDateTime.now(), LocalDateTime.now(), 10, "etc");
		// ValidBuilderUser user3 = ValidBuilderUser.builder().build();

		ValidBuilderUser user = ValidBuilderUser.builder("tester", "test@test.com").build();
		assertAll(
			() -> assertDoesNotThrow(() -> {
				em.persist(user);
			}),
			() -> assertTrue(() -> em.find(ValidBuilderUser.class, 1L).equals(user))
		);
	}

}

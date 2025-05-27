package com.uninassau.periodo3.backend.projeto.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class PessoaRepositoryTest {

	@Test
	void test() {
		assertThat(true).isTrue();
	}

}

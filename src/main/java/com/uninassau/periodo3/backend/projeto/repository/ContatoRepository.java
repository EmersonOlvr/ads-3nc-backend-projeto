package com.uninassau.periodo3.backend.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uninassau.periodo3.backend.projeto.domain.Contato;

import java.util.UUID;

public interface ContatoRepository extends JpaRepository<Contato, UUID> {
	
}

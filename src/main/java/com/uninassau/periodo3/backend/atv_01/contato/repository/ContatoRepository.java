package com.uninassau.periodo3.backend.atv_01.contato.repository;

import com.uninassau.periodo3.backend.atv_01.contato.domain.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContatoRepository extends JpaRepository<Contato, UUID> {
}

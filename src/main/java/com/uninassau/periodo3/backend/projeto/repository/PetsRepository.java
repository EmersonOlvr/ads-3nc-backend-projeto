package com.uninassau.periodo3.backend.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uninassau.periodo3.backend.projeto.domain.Pets;

import java.util.UUID;

public interface PetsRepository extends JpaRepository<Pets, UUID> {

}

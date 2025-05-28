package com.uninassau.periodo3.backend.projeto.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uninassau.periodo3.backend.projeto.domain.Pet;

public interface PetsRepository extends JpaRepository<Pet, UUID> {
	
	List<Pet> findByNomeAndRaca(String nome, String raca);

}

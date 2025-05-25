package com.uninassau.periodo3.backend.atv_01.pets.repository;

import com.uninassau.periodo3.backend.atv_01.pets.domain.Pets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetsRepository extends JpaRepository<Pets, UUID> {
}

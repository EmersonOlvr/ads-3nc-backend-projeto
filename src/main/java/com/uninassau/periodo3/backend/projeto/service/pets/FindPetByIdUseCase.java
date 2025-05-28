package com.uninassau.periodo3.backend.projeto.service.pets;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uninassau.periodo3.backend.projeto.domain.Pet;
import com.uninassau.periodo3.backend.projeto.exception.PetNotFoundByIdException;
import com.uninassau.periodo3.backend.projeto.repository.PetsRepository;

@Service
public class FindPetByIdUseCase {

	@Autowired
	private PetsRepository petsRepository;

	public Pet execute(UUID id) {
		return petsRepository.findById(id)
							.orElseThrow(PetNotFoundByIdException::new);
	}
	
}

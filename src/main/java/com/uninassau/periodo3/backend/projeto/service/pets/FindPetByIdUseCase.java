package com.uninassau.periodo3.backend.projeto.service.pets;

import com.uninassau.periodo3.backend.projeto.domain.Pets;
import com.uninassau.periodo3.backend.projeto.repository.PetsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindPetByIdUseCase {

    @Autowired
    private PetsRepository petsRepository;

    public Optional<Pets> execute(UUID id) {
        return petsRepository.findById(id);
    }
    
}

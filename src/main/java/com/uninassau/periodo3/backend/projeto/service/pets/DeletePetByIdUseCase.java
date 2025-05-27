package com.uninassau.periodo3.backend.projeto.service.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uninassau.periodo3.backend.projeto.repository.PetsRepository;

import java.util.UUID;

@Service
public class DeletePetByIdUseCase {

    @Autowired
    private PetsRepository petsRepository;

    public void execute(UUID id) {
        petsRepository.deleteById(id);
    }
    
}

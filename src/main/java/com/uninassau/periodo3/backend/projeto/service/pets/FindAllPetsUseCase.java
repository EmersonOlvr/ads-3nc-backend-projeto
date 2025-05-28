package com.uninassau.periodo3.backend.projeto.service.pets;

import com.uninassau.periodo3.backend.projeto.domain.Pet;
import com.uninassau.periodo3.backend.projeto.repository.PetsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FindAllPetsUseCase {

    @Autowired
    private PetsRepository petsRepository;

    public List<Pet> execute() {
        return petsRepository.findAll();
    }
    
}

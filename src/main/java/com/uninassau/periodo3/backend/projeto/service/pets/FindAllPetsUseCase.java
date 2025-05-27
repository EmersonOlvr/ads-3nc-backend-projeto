package com.uninassau.periodo3.backend.projeto.service.pets;

import com.uninassau.periodo3.backend.projeto.domain.Pets;
import com.uninassau.periodo3.backend.projeto.repository.PetsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FindAllPetsUseCase {

    @Autowired
    private PetsRepository petsRepository;

    public List<Pets> execute() {
        return petsRepository.findAll();
    }
    
}

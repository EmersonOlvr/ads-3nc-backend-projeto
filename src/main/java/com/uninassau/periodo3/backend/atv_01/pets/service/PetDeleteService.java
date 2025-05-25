package com.uninassau.periodo3.backend.atv_01.pets.service;

import com.uninassau.periodo3.backend.atv_01.pets.repository.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class PetDeleteService {

    @Autowired
    private PetsRepository petsRepository;

    public void delete(UUID id) {
        petsRepository.deleteById(id);
    }
}

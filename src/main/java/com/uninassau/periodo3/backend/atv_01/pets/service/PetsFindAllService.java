package com.uninassau.periodo3.backend.atv_01.pets.service;

import com.uninassau.periodo3.backend.atv_01.pets.domain.Pets;
import com.uninassau.periodo3.backend.atv_01.pets.repository.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PetsFindAllService {

    @Autowired
    private PetsRepository petsRepository;

    public List<Pets> findAll() {
        return petsRepository.findAll();
    }
}

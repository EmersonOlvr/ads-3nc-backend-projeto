package com.uninassau.periodo3.backend.atv_01.pets.service;

import com.uninassau.periodo3.backend.atv_01.pets.domain.Pets;
import com.uninassau.periodo3.backend.atv_01.pets.dto.PetDto;
import com.uninassau.periodo3.backend.atv_01.pets.repository.PetsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PetsCreateService {

    @Autowired
    private PetsRepository petsRepository;

    public Pets create(PetDto pet) {
        Pets newPet = new Pets();
        BeanUtils.copyProperties(pet, newPet);

        return petsRepository.save(newPet);
    }
}

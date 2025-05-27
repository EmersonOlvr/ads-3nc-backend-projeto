package com.uninassau.periodo3.backend.projeto.service.pets;

import com.uninassau.periodo3.backend.projeto.domain.Pets;
import com.uninassau.periodo3.backend.projeto.repository.PetsRepository;
import com.uninassau.periodo3.backend.projeto.service.pets.dto.PetDto;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CreatePetUseCase {

    @Autowired
    private PetsRepository petsRepository;

    public Pets execute(PetDto pet) {
        Pets newPet = new Pets();
        BeanUtils.copyProperties(pet, newPet);

        return petsRepository.save(newPet);
    }
    
}

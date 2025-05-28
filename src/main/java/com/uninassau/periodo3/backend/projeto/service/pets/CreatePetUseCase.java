package com.uninassau.periodo3.backend.projeto.service.pets;

import com.uninassau.periodo3.backend.projeto.domain.Pet;
import com.uninassau.periodo3.backend.projeto.repository.PetsRepository;
import com.uninassau.periodo3.backend.projeto.service.pets.dto.PetDto;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CreatePetUseCase {

    @Autowired
    private PetsRepository petsRepository;

    public Pet execute(PetDto petDto) {
        Pet newPet = new Pet();
        BeanUtils.copyProperties(petDto, newPet);

        return petsRepository.save(newPet);
    }
    
}

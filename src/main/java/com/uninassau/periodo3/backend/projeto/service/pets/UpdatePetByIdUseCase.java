package com.uninassau.periodo3.backend.projeto.service.pets;

import com.uninassau.periodo3.backend.projeto.domain.Pet;
import com.uninassau.periodo3.backend.projeto.repository.PetsRepository;
import com.uninassau.periodo3.backend.projeto.service.pets.dto.PetDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UpdatePetByIdUseCase {

    @Autowired
    private PetsRepository petsRepository;

    public Optional<Pet> execute(UUID id, PetDto attPet) {
        return petsRepository.findById(id).map(petExistente -> {
            BeanUtils.copyProperties(attPet, petExistente, "id");

            return petsRepository.save(petExistente);
        });
    }
    
}

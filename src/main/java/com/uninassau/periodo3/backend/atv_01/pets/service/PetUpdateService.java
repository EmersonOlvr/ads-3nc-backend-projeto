package com.uninassau.periodo3.backend.atv_01.pets.service;

import com.uninassau.periodo3.backend.atv_01.pets.domain.Pets;
import com.uninassau.periodo3.backend.atv_01.pets.dto.PetDto;
import com.uninassau.periodo3.backend.atv_01.pets.repository.PetsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetUpdateService {

    @Autowired
    private PetsRepository petsRepository;

    public Optional<Pets> update(UUID id, PetDto attPet) {
        return petsRepository.findById(id).map(petExistente -> {
            BeanUtils.copyProperties(attPet, petExistente, "id");

            return petsRepository.save(petExistente);
        });
    }
}

package com.uninassau.periodo3.backend.projeto.service.pets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uninassau.periodo3.backend.projeto.domain.Pet;
import com.uninassau.periodo3.backend.projeto.repository.PetsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ListPetsByNomeAndRacaUseCase {

    @Autowired
    private PetsRepository petsRepository;

    public List<Pet> execute(String nome, String raca) {
		log.info(String.format(
				"Buscando pets com o nome \"%s\" e raça \"%s\"...", 
				nome, raca
		));
		List<Pet> pets = petsRepository.findByNomeAndRaca(nome, raca);
		log.info(String.format("%s pet(s) encontrado(s)", pets.size()));
		
        return pets;
    }
    
}

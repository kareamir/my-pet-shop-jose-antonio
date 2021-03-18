package com.shop.mypetshop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.shop.mypetshop.domain.NewPetDto;
import com.shop.mypetshop.domain.Pet;
import com.shop.mypetshop.domain.PetDto;
import com.shop.mypetshop.repo.BreedRepository;
import com.shop.mypetshop.repo.PetRepository;
import com.shop.mypetshop.repo.SpecieRepository;

@Service
public class PetService {

    private final PetRepository repo;
    private final BreedRepository breedRepo;
    private final SpecieRepository specieRepo;

    public PetService(final PetRepository repo,final BreedRepository breedRepo,final SpecieRepository specieRepo)
    {
        this.repo = repo;
        this.breedRepo = breedRepo;
        this.specieRepo = specieRepo;
        
    }
    public PetDto add(NewPetDto petDto) {
    	
    	Pet pet=repo.save(getEntity(petDto));
    	
    	return getDto(pet);
    }
    public List<PetDto> getAll(){
    	
    	List<PetDto> dtos=new ArrayList<>();
    		
    	for(Pet pet:repo.findAll()) {    		
			dtos.add(getDto(pet));
    	}
    	return dtos;
    }
    PetDto getDto(Pet pet){
    	PetDto petDto=new PetDto();
		BeanUtils.copyProperties(pet, petDto);
		petDto.setBreed(pet.getBreed().getName());
		petDto.setSpecie(pet.getBreed().getSpecie().getName());
		return petDto;
    }
    Pet getEntity(NewPetDto petDto){
    	Pet pet=new Pet();
		BeanUtils.copyProperties(petDto,pet);		
		pet.setBreed(breedRepo.getOne(petDto.getIdBreed().longValue()));
		return pet;
    }
    

}

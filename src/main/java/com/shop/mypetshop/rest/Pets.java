package com.shop.mypetshop.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.mypetshop.domain.NewPetDto;
import com.shop.mypetshop.domain.Pet;
import com.shop.mypetshop.domain.PetDto;
import com.shop.mypetshop.services.PetService;


/**
 * Rest controller used to expose REST endpoints for operations with {@link Pet} objects.
 */
@CrossOrigin
@RestController
@RequestMapping("/pets")
public class Pets
{
    private final PetService service;

    public Pets(final PetService service)
    {
        this.service= service;
    }

    @GetMapping
    public List<PetDto> getAll()
    {
        return service.getAll();
    }
    @PostMapping
    public ResponseEntity<?> addPet(@RequestBody NewPetDto newPetDto) {
    	try {
        	PetDto dto=service.add(newPetDto);
    		return new ResponseEntity<PetDto>(dto,HttpStatus.ACCEPTED);
    		
    	}catch(Exception e) {
    		return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
    		
    	}
    	
    }
}

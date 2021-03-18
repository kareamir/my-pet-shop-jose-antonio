package com.shop.mypetshop.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shop.mypetshop.domain.NewPetDto;
import com.shop.mypetshop.domain.Pet;
import com.shop.mypetshop.domain.PetDto;
import com.shop.mypetshop.domain.Specie;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PetsTest
{
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_getAll()
    {
        final ResponseEntity<PetDto[]> response = restTemplate.getForEntity(createBaseUrl() + "/pets", PetDto[].class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        final List<PetDto> responsePets = Arrays.asList(response.getBody());
        assertEquals(11, responsePets.size());
        responsePets.forEach(dto -> {
            assertNotNull(dto.getId());
            assertNotNull(dto.getName());
            assertNotNull(dto.getBreed());

            assertNotNull(dto.getSpecie());
        });
    }
    @Test
    public void test_getAddNew()
    {
    	NewPetDto newDto=new NewPetDto();
    	//newDto.setName("Max");
    	//newDto.setIdBreed(3);
        final ResponseEntity<PetDto> response = restTemplate.postForEntity(createBaseUrl() + "/pets", newDto,PetDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        final PetDto responsePet = response.getBody();
      
        assertNotNull(responsePet.getSpecie());
        
        
      
    }

    private String createBaseUrl()
    {
        return String.format("http://localhost:%s", port);
    }
}

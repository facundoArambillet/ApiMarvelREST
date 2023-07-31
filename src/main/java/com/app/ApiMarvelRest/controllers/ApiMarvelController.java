package com.app.ApiMarvelRest.controllers;

import com.app.TestJava.models.MarvelCharacter;

import com.app.TestJava.services.ApiMarvelService;
import com.app.ApiMarvelRest.models.RequestTime;
import com.app.ApiMarvelRest.services.ApiMarvelRequestTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.List;

//Tiene que ser RestController para que funcione la documentacion en swagger
@RestController
@RequestMapping("/marvel")
public class ApiMarvelController {
    //Si le pongo @Autowired me sale un error de un @Bean
    @Autowired()
    private ApiMarvelService apiMarvelService;
    @Autowired
    private ApiMarvelRequestTimeService marvelApiRequestTimeService;

//    @Operation(summary = "Get all characters")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Characters found",
//                    content = { @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = MarvelCharacter.class)) }),
//
//    })
    @GetMapping()
    public ResponseEntity<List<MarvelCharacter>> getAll() {
        if(apiMarvelService.getAll() != null) {
            LocalDateTime time = LocalDateTime.now();
            RequestTime marvelApiRequestTime = new RequestTime(time);
            marvelApiRequestTimeService.createRequestTime(marvelApiRequestTime);
            return new ResponseEntity<List<MarvelCharacter>>(apiMarvelService.getAll(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<List<MarvelCharacter>>(apiMarvelService.getAll(), HttpStatus.NOT_FOUND);
        }
    }

//    @Operation(summary = "Get a character by id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Found the character",
//                    content = { @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = MarvelCharacter.class)) }),
//            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
//                    content = @Content),
//            @ApiResponse(responseCode = "404", description = "Character not found",
//                    content = @Content)
//
//    })
    @GetMapping("/{characterId}")
    public ResponseEntity<MarvelCharacter> getById(@PathVariable("characterId") int id) {
        if(apiMarvelService.getById(id) != null) {
            LocalDateTime time = LocalDateTime.now();
            RequestTime marvelApiRequestTime = new RequestTime(time);
            marvelApiRequestTimeService.createRequestTime(marvelApiRequestTime);
            return new ResponseEntity<MarvelCharacter>(apiMarvelService.getById(id), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MarvelCharacter>(apiMarvelService.getById(id), HttpStatus.NOT_FOUND);
        }
    }

}

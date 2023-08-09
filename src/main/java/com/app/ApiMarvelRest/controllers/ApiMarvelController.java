package com.app.ApiMarvelRest.controllers;

import com.app.ApiMarvelRest.services.AuthService;
import com.app.TestJava.models.MarvelCharacter;
import com.app.TestJava.services.ApiMarvelService;
import com.app.ApiMarvelRest.models.RequestTime;
import com.app.ApiMarvelRest.services.ApiMarvelRequestTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

//Tiene que ser RestController para que funcione la documentacion en swagger
@RestController
@RequestMapping("/marvel")
public class ApiMarvelController {
    //Si le pongo @Autowired me sale un error de un @Bean
    @Autowired
    private AuthService authService;
    @Autowired
    private ApiMarvelService apiMarvelService;
    @Autowired
    private ApiMarvelRequestTimeService marvelApiRequestTimeService;

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

    //Endpoint usando @PathVariable
//    @GetMapping("/limit/{limit}/{pageNumber}")
//    public ResponseEntity<List<MarvelCharacter>> getByLimit(@PathVariable int limit, @PathVariable int pageNumber) {
//        int offset = (pageNumber - 1) * limit;
//        if(apiMarvelService.getByLimit(limit,offset) != null) {
//            LocalDateTime time = LocalDateTime.now();
//            RequestTime marvelApiRequestTime = new RequestTime(time);
//            marvelApiRequestTimeService.createRequestTime(marvelApiRequestTime);
//            return new ResponseEntity<List<MarvelCharacter>>(apiMarvelService.getByLimit(limit,offset), HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<List<MarvelCharacter>>(apiMarvelService.getByLimit(limit,offset), HttpStatus.NOT_FOUND);
//        }
//    }

    //Endpoint usando @RequestParam(Dejo este porque es la primera vez que uso este decorador)
    @GetMapping("/limit")
    public ResponseEntity<List<MarvelCharacter>> getByLimit(@RequestParam int limit, @RequestParam int pageNumber) {
        int offset = (pageNumber - 1) * limit;
        if(apiMarvelService.getByLimit(limit,offset) != null) {
            LocalDateTime time = LocalDateTime.now();
            RequestTime marvelApiRequestTime = new RequestTime(time);
            marvelApiRequestTimeService.createRequestTime(marvelApiRequestTime);
            return new ResponseEntity<List<MarvelCharacter>>(apiMarvelService.getByLimit(limit,offset), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<List<MarvelCharacter>>(apiMarvelService.getByLimit(limit,offset), HttpStatus.NOT_FOUND);
        }
    }

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

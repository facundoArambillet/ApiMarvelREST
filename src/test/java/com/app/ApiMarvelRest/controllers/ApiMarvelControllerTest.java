package com.app.ApiMarvelRest.controllers;

import com.app.ApiMarvelRest.services.ApiMarvelRequestTimeService;
import com.app.TestJava.models.*;
import com.app.TestJava.services.ApiMarvelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApiMarvelControllerTest {

    @Mock
    private MarvelSeries marvelSeries;
    @Mock
    private MarvelStories marvelStories;
    @Mock
    private MarvelEvents marvelEvents;
    //Le saque la anotacion @Mock porque los arrays son clases finales y mockito no las puede crear
    private MarvelUrls[] marvelUrls;
    @Mock
    private MarvelComic marvelComic;
    @Mock
    private MarvelThumbail marvelThumbail;
    @Mock
    private MarvelCharacter marvelCharacter;
    @Mock
    private ApiMarvelService apiMarvelService;
    @Mock
    private ApiMarvelRequestTimeService marvelApiRequestTimeService;
    @InjectMocks
    private ApiMarvelController apiMarvelController;

      @BeforeEach
      private void setUp() {
          marvelSeries = new MarvelSeries();
          marvelStories = new MarvelStories();
          marvelEvents = new MarvelEvents();
          marvelComic = new MarvelComic();
          marvelThumbail = new MarvelThumbail();
          marvelUrls = new MarvelUrls[0];
          marvelCharacter = new MarvelCharacter(1,"Character 1","...","...",marvelThumbail,"...",marvelComic,marvelSeries,marvelStories
          ,marvelEvents,marvelUrls);
      }

    @Test
    public void givenExistingCharacters_whenGetAll_thenReturnOk() {
        List<MarvelCharacter> marvelCharacters = new ArrayList<>();
        MarvelCharacter marvelCharacter_2 = new MarvelCharacter(2,"Character 2","...","...",marvelThumbail,"...",marvelComic,marvelSeries,marvelStories
                ,marvelEvents,marvelUrls);
        marvelCharacters.add(marvelCharacter);
        marvelCharacters.add(marvelCharacter_2);
        when(apiMarvelService.getAll()).thenReturn(marvelCharacters);

        ResponseEntity<List<MarvelCharacter>> response = apiMarvelController.getAll();

        assertTrue(response.getStatusCode() == HttpStatus.OK,"El estatus deberia ser OK");
        assertEquals(response.getBody(),marvelCharacters,"Las listas deberian ser iguales");
    }

    @Test
    public void givenExistingCharacterId_whenGetById_thenReturnOk() {
        int characterID = 1;
        when(apiMarvelService.getById(characterID)).thenReturn(marvelCharacter);

        ResponseEntity<MarvelCharacter> response = apiMarvelController.getById(characterID);

        assertTrue(response.getStatusCode() == HttpStatus.OK);
        assertEquals(response.getBody(), marvelCharacter );
    }


    @Test
    public void givenNonExistingCharacterId_whenGetById_thenReturnNotFound() {
        int nonExistingCharacterID = 999; // ID que no existe
        when(apiMarvelService.getById(nonExistingCharacterID)).thenReturn(null);

        ResponseEntity<MarvelCharacter> response = apiMarvelController.getById(nonExistingCharacterID);

        assertTrue(response.getStatusCode() == HttpStatus.NOT_FOUND);
        assertEquals(response.getBody(), null);
    }


    @Test
    public void givenLimitAndPageNumber_whenGetByLimit_thenReturnOk() {
        int limit = 1;
        int pageNumber = 1;
        int offset = (pageNumber - 1) * limit;

        MarvelCharacter marvelCharacter_2 = new MarvelCharacter(2,"Character 2","...","...",marvelThumbail,"...",marvelComic,marvelSeries,marvelStories
                ,marvelEvents,marvelUrls);
        List<MarvelCharacter> marvelCharacters = new ArrayList<>();
        List<MarvelCharacter> marvelCharacterByLimit = new ArrayList<>();
        marvelCharacterByLimit.add(marvelCharacter);
        marvelCharacters.add(marvelCharacter);
        marvelCharacters.add(marvelCharacter_2);

        when(apiMarvelService.getByLimit(limit, offset)).thenReturn(marvelCharacterByLimit);
        ResponseEntity<List<MarvelCharacter>> response = apiMarvelController.getByLimit(limit, pageNumber);

        assertTrue(response.getStatusCode() == HttpStatus.OK,"El estatus deberia ser OK");
        assertEquals(response.getBody(), marvelCharacterByLimit,"Las listas deberian ser iguales");
    }


}


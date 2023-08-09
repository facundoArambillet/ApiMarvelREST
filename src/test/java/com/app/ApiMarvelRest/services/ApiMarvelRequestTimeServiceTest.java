package com.app.ApiMarvelRest.services;

import com.app.ApiMarvelRest.models.RequestTime;
import com.app.ApiMarvelRest.repositories.ApiMarvelRequestTimeRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ApiMarvelRequestTimeServiceTest {

    @Mock
    private ApiMarvelRequestTimeRepository apiMarvelRequestTimeRepository;

    @InjectMocks
    private ApiMarvelRequestTimeService marvelRequestTimeService;
//  @BeforeEach
//  public void setUp() {
//    MockitoAnnotations.initMocks(this);
//  }
    @Test
    public void givenRequestTime_whenCreateRequestTime_thenCreatedTimeShouldBeEqualToInputTime() {
        LocalDateTime time = LocalDateTime.now();
        RequestTime requestTime = new RequestTime();
        requestTime.setTime(time);

        when(apiMarvelRequestTimeRepository.save(requestTime)).thenReturn(requestTime);
        RequestTime result = marvelRequestTimeService.createRequestTime(requestTime);

        assertEquals(requestTime, result);
    }

    @Test
    public void givenTwoStoredRequestTimes_whenGetAllRequestTimes_thenListShouldNotBeEmptyAndFirstRequestTimeShouldHaveNonNullID() {
        // Given - Dos tiempos de solicitud almacenados en el repositorio
        LocalDateTime time = LocalDateTime.now();
        RequestTime requestTime1 = new RequestTime(time);
        requestTime1.setId(1L);

        LocalDateTime time2 = LocalDateTime.now();
        RequestTime requestTime2 = new RequestTime();
        requestTime2.setId(2L);

        // When - Obteniendo todos los tiempos de solicitud
        when(apiMarvelRequestTimeRepository.findAll()).thenReturn(Arrays.asList(requestTime1, requestTime2));
        List<RequestTime> result = marvelRequestTimeService.getAllRequestTimes();

        // Then - La lista no debería estar vacía y el primer tiempo de solicitud debería tener un ID no nulo
        assertTrue(!result.isEmpty(), "La lista de tiempos de solicitud no debería estar vacía");
        assertTrue(result.get(0).getId() != null, "El ID del primer tiempo de solicitud no deberia ser nulo");
    }
}
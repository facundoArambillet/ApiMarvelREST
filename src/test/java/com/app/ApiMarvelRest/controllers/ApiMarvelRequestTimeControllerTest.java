package com.app.ApiMarvelRest.controllers;

import com.app.ApiMarvelRest.models.RequestTime;
import com.app.ApiMarvelRest.services.ApiMarvelRequestTimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApiMarvelRequestTimeControllerTest {

    @Mock
    private RequestTime requestTime_1;

    @Mock
    private RequestTime requestTime_2;

    @Mock
    private ApiMarvelRequestTimeService apiMarvelRequestTimeService;

    @InjectMocks
    private ApiMarvelRequestTimeController apiMarvelRequestTimeController;

    @BeforeEach
    public void setUp() {
        LocalDateTime time = LocalDateTime.now();
        requestTime_1 = new RequestTime();
        requestTime_1.setTime(time);
        LocalDateTime time_2 = LocalDateTime.now();
        requestTime_2 = new RequestTime();
        requestTime_2.setTime(time_2);
        apiMarvelRequestTimeService.createRequestTime(requestTime_1);
        apiMarvelRequestTimeService.createRequestTime(requestTime_2);
    }

    @Test
    public void givenExistingRequestTimes_whenGetAll_thenReturnListOfRequestTimes() {
        List<RequestTime> requestTimes = new ArrayList<>();
        requestTimes.add(requestTime_1);
        requestTimes.add(requestTime_2);

        when(apiMarvelRequestTimeService.getAllRequestTimes()).thenReturn(requestTimes);

        List<RequestTime> response = apiMarvelRequestTimeController.getAll();

        assertFalse(response.isEmpty(), "La lista no debería estar vacía");
        assertEquals(requestTimes, response, "Las listas deberían ser iguales");
    }

}
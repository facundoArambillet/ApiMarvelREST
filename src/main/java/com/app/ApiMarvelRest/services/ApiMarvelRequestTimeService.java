package com.app.ApiMarvelRest.services;

import com.app.ApiMarvelRest.models.RequestTime;
import com.app.ApiMarvelRest.repositories.ApiMarvelRequestTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiMarvelRequestTimeService {
    @Autowired
    private ApiMarvelRequestTimeRepository marvelApiRequestTimeRepository;

    public RequestTime createRequestTime(RequestTime time) {
       return marvelApiRequestTimeRepository.save(time);
    }

    public List<RequestTime> getAllRequestTimes() {
        return marvelApiRequestTimeRepository.findAll();
    }
}

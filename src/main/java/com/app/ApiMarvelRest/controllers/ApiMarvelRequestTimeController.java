package com.app.ApiMarvelRest.controllers;

import com.app.ApiMarvelRest.models.RequestTime;
import com.app.ApiMarvelRest.services.ApiMarvelRequestTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/time")
public class ApiMarvelRequestTimeController {

    @Autowired
    private ApiMarvelRequestTimeService apiMarvelRequestTimeService;
    @GetMapping()
    public List<RequestTime> getAll() {
        return apiMarvelRequestTimeService.getAllRequestTimes();
    }
}

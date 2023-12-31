package com.app.ApiMarvelRest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;
@Entity
@Data
public class RequestTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime time;

    public RequestTime() {}

    public RequestTime(LocalDateTime time) {
        this.time = time;
    }

}

package com.app.ApiMarvelRest.repositories;

import com.app.ApiMarvelRest.models.RequestTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiMarvelRequestTimeRepository extends JpaRepository<RequestTime,Long> {
}

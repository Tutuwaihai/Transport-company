package com.transportcompany.transport_app.repository

import com.transportcompany.transport_app.model.City
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CityRepository : JpaRepository<City, Long> {
    @Query("SELECT c " +
            "FROM City c " +
            "WHERE c.isDeleted = 0")
    fun findAllCities(): List<City>
}
package com.transportcompany.transport_app.repository

import com.transportcompany.transport_app.model.Trip
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TripRepository : JpaRepository<Trip, Long> {
    @Query("SELECT t FROM Trip t WHERE t.isDeleted = 0")
    fun findAllTrips(): List<Trip>
}
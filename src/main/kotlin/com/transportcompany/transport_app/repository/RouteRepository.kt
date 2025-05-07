package com.transportcompany.transport_app.repository

import com.transportcompany.transport_app.model.Route
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RouteRepository : JpaRepository<Route, Long> {
    fun findAllByIsDeleted(isDeleted: Int = 0): List<Route>
}
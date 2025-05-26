package com.transportcompany.transport_app.repository

import com.transportcompany.transport_app.model.Transport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TransportRepository : JpaRepository<Transport, Long> {

    @Query("""
        SELECT t FROM Transport t
        LEFT JOIN FETCH t.city
        WHERE t.isDeleted = 0
    """)
    fun findAllTransport(): List<Transport>
}
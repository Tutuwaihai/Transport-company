package com.transportcompany.transport_app.repository

import com.transportcompany.transport_app.model.Transport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TransportRepository : JpaRepository<Transport, Long> {

    @Query("""
        SELECT t, t.mark, t.licensePlate, t.tonnage
        FROM Transport t
        left join fetch t.city
        where t.isDeleted = 0
    """)
    fun findAllTransport(): List<Transport>
}
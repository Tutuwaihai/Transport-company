package com.transportcompany.transport_app.repository

import com.transportcompany.transport_app.model.Transport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TransportRepository : JpaRepository<Transport, Long> {

    @Query(
        value = """
            SELECT 
                t.id, 
                t.mark, 
                t.license_plate, 
                t.tonnage, 
                c.title AS cityTitle
            FROM transport t
            LEFT JOIN city c ON t.idcity = c.id
            WHERE t.isdeleted = 0
        """,
        nativeQuery = true
    )
    fun findAllTransport(): List<Map<String, Any>>
}
package com.transportcompany.transport_app.repository

import com.transportcompany.transport_app.model.Trailer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TrailerRepository : JpaRepository<Trailer, Long> {

    @Query(
        value = """
            SELECT t.id, t.model, t.tonnage, t.volume
            FROM model_transport t
            WHERE t.isdeleted = 0 AND (t.type = 2 OR t.type = 3)
        """,
        nativeQuery = true
    )
    fun findAllTrailers(): List<Map<String, Any>>
}
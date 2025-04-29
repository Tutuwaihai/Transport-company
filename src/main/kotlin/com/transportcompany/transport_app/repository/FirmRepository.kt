package com.transportcompany.transport_app.repository

import com.transportcompany.transport_app.model.Firm
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FirmRepository : JpaRepository<Firm, Long> {

    @Query("SELECT f " +
            "FROM Firm f " +
            "WHERE f.isDeleted = 0")
    fun findAllActiveFirms(): List<Firm>
}
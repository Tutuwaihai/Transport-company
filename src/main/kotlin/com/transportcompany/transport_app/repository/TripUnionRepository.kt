package com.transportcompany.transport_app.repository

import com.transportcompany.transport_app.model.TripUnion
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TripUnionRepository : JpaRepository<TripUnion, Long> {
    
    @Query("""
        SELECT t FROM TripUnion t
        LEFT JOIN FETCH t.route
        LEFT JOIN FETCH t.transport
        LEFT JOIN FETCH t.trailer
        LEFT JOIN FETCH t.employee
        LEFT JOIN FETCH t.firmCarrier
        LEFT JOIN FETCH t.firmCustomer
        WHERE t.id = :id AND t.isDeleted = 0
    """)
    fun findByIdWithRelations(id: Long): TripUnion?

    @Query("""
        SELECT DISTINCT t FROM TripUnion t
        LEFT JOIN FETCH t.route
        LEFT JOIN FETCH t.transport
        LEFT JOIN FETCH t.trailer
        LEFT JOIN FETCH t.employee
        LEFT JOIN FETCH t.firmCarrier
        LEFT JOIN FETCH t.firmCustomer
        WHERE t.isDeleted = 0
    """)
    fun findAllWithRelations(): List<TripUnion>

    @Query("""
        SELECT t FROM TripUnion t
        LEFT JOIN t.route
        LEFT JOIN t.transport
        LEFT JOIN t.trailer
        LEFT JOIN t.employee
        LEFT JOIN t.firmCarrier
        LEFT JOIN t.firmCustomer
        WHERE t.isDeleted = 0 AND t.isActive = 1
    """, countQuery = """
        SELECT COUNT(t) FROM TripUnion t
        WHERE t.isDeleted = 0 AND t.isActive = 1
    """)
    fun findAllActiveAndNotDeleted(pageable: Pageable): Page<TripUnion>
}
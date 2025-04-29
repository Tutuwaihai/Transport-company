package com.transportcompany.transport_app.repository

import com.transportcompany.transport_app.model.TripUnion
import org.springframework.data.jpa.repository.JpaRepository

interface TripUnionRepository : JpaRepository<TripUnion, Long>
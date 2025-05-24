package com.transportcompany.transport_app.repository

import com.transportcompany.transport_app.model.SendingMoving
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SendingMovingRepository : JpaRepository<SendingMoving, Long> {
    fun findAllByIsDeletedAndTypePointerAndIdPointer(
        isDeleted: Int,
        typePointer: Int,
        idPointer: Long
    ): List<SendingMoving>
    fun findByTypePointerAndIdPointer(typePointer: Int, idPointer: Long): List<SendingMoving>

}
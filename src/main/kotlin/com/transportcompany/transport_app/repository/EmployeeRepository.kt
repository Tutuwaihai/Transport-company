package com.transportcompany.transport_app.repository

import com.transportcompany.transport_app.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long> {

    @Query(
        value = """
            SELECT 
                e.id AS id, 
                e.fio AS fio, 
                c.title AS cityTitle
            FROM employee e
            LEFT JOIN city c ON e.idcity = c.id
            WHERE e.isdeleted = 0 
              AND e.worker_type = 4
        """,
        nativeQuery = true
    )
    fun findAllDrivers(): List<Map<String, Any>>
}
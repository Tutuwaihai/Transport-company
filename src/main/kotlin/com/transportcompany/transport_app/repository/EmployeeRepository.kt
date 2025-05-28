package com.transportcompany.transport_app.repository

import com.transportcompany.transport_app.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long> {

    @Query("""
        SELECT e FROM Employee e
        LEFT JOIN FETCH e.city
        WHERE e.isDeleted = 0 AND e.workerType = 4
    """)
    fun findAllDrivers(): List<Employee>
}
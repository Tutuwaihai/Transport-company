package com.transportcompany.transport_app.service

import com.transportcompany.transport_app.dto.EmployeeDto
import com.transportcompany.transport_app.repository.EmployeeRepository
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository
) {
    fun getAllDrivers(): List<EmployeeDto> {
        return employeeRepository.findAllDrivers().map { row ->
            EmployeeDto(
                id = (row["id"] as Number).toLong(),
                fio = row["fio"] as String,
                cityTitle = row["cityTitle"] as? String ?: "Неизвестный город"
            )
        }
    }
}
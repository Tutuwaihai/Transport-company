package com.transportcompany.transport_app.dto.mappers

import com.transportcompany.transport_app.dto.EmployeeDto
import com.transportcompany.transport_app.model.Employee
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
interface EmployeeMapper {
    @Mapping(source = "city.title", target = "cityTitle", defaultValue = "Неизвестно")
    fun toDtoList(employees: List<Employee>): List<EmployeeDto>
}
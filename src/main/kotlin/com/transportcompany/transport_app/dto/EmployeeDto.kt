package com.transportcompany.transport_app.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.transportcompany.transport_app.model.City

data class EmployeeDto(
    val id: Long,
    val fio: String,
    val cityTitle: String? = null
)

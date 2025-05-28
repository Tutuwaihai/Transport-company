package com.transportcompany.transport_app.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.transportcompany.transport_app.model.City

data class TransportDto(
    val id: Long,
    val mark: String?,
    val licensePlate: String?,
    val tonnage: Double?,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val city: City? = null
) {
    val cityTitle: String?
        get() = city?.title
}
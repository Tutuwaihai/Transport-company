package com.transportcompany.transport_app.dto

import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class TripUnionRequest(
    @field:NotNull(message = "Фирма-заказчик обязательна")
    val idFirmCustomer: Long?,

    @field:NotNull(message = "Фирма-перевозчик обязательна")
    val idFirmCarrier: Long?,

    @field:NotNull(message = "Маршрут обязателен")
    val idRoute: Long?,

    @field:NotNull(message = "Водитель обязателен")
    val idEmployee: Long?,

    @field:NotNull(message = "Транспорт обязателен")
    val idTransport: Long?,

    val idTrailer: Long? = null,
    val costs: BigDecimal? = null,
    val description: String? = null
)


package com.transportcompany.transport_app.dto

data class SendingMovingDto(
    val id: Long,
    val idSending: Long,
    val typePointer: Int,
    val idPointer: Long?,
    val numCount: Int?,
    val numPlaces: Int?
)
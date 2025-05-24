package com.transportcompany.transport_app.controller

import com.transportcompany.transport_app.dto.SendingMovingDto
import com.transportcompany.transport_app.service.SendingMovingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sending-movings")
class SendingMovingController(
    private val service: SendingMovingService
) {
    @GetMapping("/trips/{tripId}")
    fun getInvoicesInTrip(@PathVariable tripId: Long): List<SendingMovingDto> {
        return service.getInvoicesInTrip(tripId)
    }

    @GetMapping("/warehouses/{warehouseId}")
    fun getSendingMovingsByWarehouse(
        @PathVariable warehouseId: Long,
        @RequestParam(required = false) filter: String?
    ): List<SendingMovingDto> {
        return service.getFilteredSendingMovingsByWarehouse(warehouseId, filter)
    }

    @PutMapping("/move-to-trip/{tripId}")
    fun moveToTrip(
        @PathVariable tripId: Long,
        @RequestBody sendingIds: List<Long>
    ): ResponseEntity<Void> {
        service.moveToTrip(sendingIds, tripId)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/move-to-warehouse/{warehouseId}")
    fun moveToWarehouse(
        @PathVariable warehouseId: Long,
        @RequestBody sendingIds: List<Long>
    ): ResponseEntity<Void> {
        service.moveToWarehouse(sendingIds, warehouseId)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/move-all-from-warehouse/{warehouseId}/to-trip/{tripId}")
    fun moveAllFromWarehouseToTrip(
        @PathVariable warehouseId: Long,
        @PathVariable tripId: Long
    ): ResponseEntity<Void> {
        service.moveAllFromWarehouseToTrip(warehouseId, tripId)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/move-all-from-trip/{tripId}/to-warehouse/{warehouseId}")
    fun moveAllFromTripToWarehouse(
        @PathVariable tripId: Long,
        @PathVariable warehouseId: Long
    ): ResponseEntity<Void> {
        service.moveAllFromTripToWarehouse(tripId, warehouseId)
        return ResponseEntity.ok().build()
    }
}

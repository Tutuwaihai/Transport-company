package com.transportcompany.transport_app.dto.mappers

import org.mapstruct.Mapper
import com.transportcompany.transport_app.dto.TripUnionRequest
import com.transportcompany.transport_app.dto.TripUnionResponse
import com.transportcompany.transport_app.model.TripUnion
import org.mapstruct.Mapping
import org.springframework.beans.factory.annotation.Autowired
import com.transportcompany.transport_app.repository.RouteRepository
import com.transportcompany.transport_app.repository.TransportRepository
import com.transportcompany.transport_app.repository.EmployeeRepository
import com.transportcompany.transport_app.repository.FirmRepository
import java.time.LocalDateTime
import jakarta.persistence.EntityNotFoundException

@Mapper(componentModel = "spring")
abstract class TripUnionMapper {

    @Autowired
    protected lateinit var routeRepository: RouteRepository

    @Autowired
    protected lateinit var transportRepository: TransportRepository

    @Autowired
    protected lateinit var employeeRepository: EmployeeRepository

    @Autowired
    protected lateinit var firmRepository: FirmRepository

    fun toEntity(dto: TripUnionRequest, userId: Long): TripUnion {
        val route = dto.idRoute?.let { 
            routeRepository.findById(it).orElseThrow { 
                EntityNotFoundException("Маршрут с id=$it не найден") 
            }
        }
        
        val transport = dto.idTransport?.let { 
            transportRepository.findById(it).orElseThrow { 
                EntityNotFoundException("Транспорт с id=$it не найден") 
            }
        }
        
        val trailer = if (dto.idTrailer == -1L) null else dto.idTrailer?.let { 
            transportRepository.findById(it).orElseThrow { 
                EntityNotFoundException("Прицеп с id=$it не найден")
            }
        }
        
        val employee = dto.idEmployee?.let { 
            employeeRepository.findById(it).orElseThrow { 
                EntityNotFoundException("Водитель с id=$it не найден") 
            }
        }
        
        val firmCarrier = dto.idFirmCarrier?.let { 
            firmRepository.findById(it).orElseThrow { 
                EntityNotFoundException("Фирма-перевозчик с id=$it не найдена") 
            }
        }
        
        val firmCustomer = dto.idFirmCustomer?.let { 
            firmRepository.findById(it).orElseThrow { 
                EntityNotFoundException("Фирма-заказчик с id=$it не найдена") 
            }
        }

        return TripUnion(
            createDate = LocalDateTime.now(),
            modifyDate = LocalDateTime.now(),
            createUser = userId,
            modifyUser = null,
            isDeleted = 0,
            isActive = 0,
            route = route,
            transport = transport,
            trailer = trailer,
            employee = employee,
            firmCarrier = firmCarrier,
            firmCustomer = firmCustomer,
            costs = dto.costs,
            description = dto.description
        )
    }

    @Mapping(target = "idRoute", source = "route.id")
    @Mapping(target = "idTransport", source = "transport.id")
    @Mapping(target = "idTrailer", source = "trailer.id")
    @Mapping(target = "idEmployee", source = "employee.id")
    @Mapping(target = "idFirmCarrier", source = "firmCarrier.id")
    @Mapping(target = "idFirmCustomer", source = "firmCustomer.id")
    abstract fun toResponse(entity: TripUnion): TripUnionResponse
    abstract fun toList(entity: List<TripUnion>): List<TripUnionResponse>

    fun updateTripUnionFromRequest(dto: TripUnionRequest, entity: TripUnion, userId: Long): TripUnion {
        val route = dto.idRoute?.let { 
            routeRepository.findById(it).orElseThrow { 
                EntityNotFoundException("Маршрут с id=$it не найден") 
            }
        } ?: entity.route

        val transport = dto.idTransport?.let { 
            transportRepository.findById(it).orElseThrow { 
                EntityNotFoundException("Транспорт с id=$it не найден") 
            }
        } ?: entity.transport

        val trailer = when {
            dto.idTrailer == -1L -> null
            dto.idTrailer != null -> transportRepository.findById(dto.idTrailer).orElseThrow {
                EntityNotFoundException("Прицеп с id=${dto.idTrailer} не найден")
            }
            else -> entity.trailer
        }

        val employee = dto.idEmployee?.let { 
            employeeRepository.findById(it).orElseThrow { 
                EntityNotFoundException("Водитель с id=$it не найден") 
            }
        } ?: entity.employee

        val firmCarrier = dto.idFirmCarrier?.let { 
            firmRepository.findById(it).orElseThrow { 
                EntityNotFoundException("Фирма-перевозчик с id=$it не найдена") 
            }
        } ?: entity.firmCarrier

        val firmCustomer = dto.idFirmCustomer?.let { 
            firmRepository.findById(it).orElseThrow { 
                EntityNotFoundException("Фирма-заказчик с id=$it не найдена") 
            }
        } ?: entity.firmCustomer

        return entity.copy(
            modifyDate = LocalDateTime.now(),
            modifyUser = userId,
            route = route,
            transport = transport,
            trailer = trailer,
            employee = employee,
            firmCarrier = firmCarrier,
            firmCustomer = firmCustomer,
            costs = dto.costs ?: entity.costs,
            description = dto.description ?: entity.description
        )
    }
}

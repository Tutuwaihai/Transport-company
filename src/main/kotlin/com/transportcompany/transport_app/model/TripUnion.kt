package com.transportcompany.transport_app.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "trip_union")
data class TripUnion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "modifydate")
    var modifyDate: LocalDateTime? = null,

    @Column(name = "createdate")
    val createDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "createuser")
    val createUser: Long,

    @Column(name = "modifyuser")
    val modifyUser: Long,

    @Column(name = "isdeleted")
    val isDeleted: Int = 0,

    @Column(name = "idroute")
    var idRoute: Long?,

    @Column(name = "idtransport")
    var idTransport: Long?,

    @Column(name = "idtrailer")
    var  idTrailer: Long? = null,

    @Column(name = "idemployee")
    var idEmployee: Long?,

    @Column(name = "costs")
    var costs: BigDecimal? = null,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "is_active")
    var isActive: Int = 0,

    @Column(name = "id_firm_carrier")
    var idFirmCarrier: Long?,

    @Column(name = "id_firm_customer")
    var idFirmCustomer: Long?

)

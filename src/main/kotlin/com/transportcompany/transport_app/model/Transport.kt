package com.transportcompany.transport_app.model

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "transport")
data class Transport (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "modifydate")
    val modifydate: LocalDateTime? = null,
    @Column(name = "createdate")
    val createdate: LocalDateTime? = null,
    @Column(name = "isdeleted")
    val isDeleted: Int = 0,
    @Column(name = "mark")
    val mark: String? = null,
    @Column(name = "licensePlate")
    val licensePlate: String? = null,
    @Column(name = "places")
    val places: Int? = null,
    @Column(name = "tonnage")
    val tonnage: Double? = null,
    @Column(name = "volume")
    val volume: Double? = null,
    @Column(name = "width")
    val width: Double? = null,
    @Column(name = "height")
    val height: Double? = null,
    @Column(name = "length")
    val length: Double? = null,
    @JoinColumn(name = "idcity")
    @OneToOne
    val city: City? = null,
    @Column(name = "description")
    val description: String? = null

)

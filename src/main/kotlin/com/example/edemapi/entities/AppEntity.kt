package com.example.edemapi.entities

import javax.persistence.*

@Entity
@Table(name = "app")
data class AppEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id : Long? = null,
    var appPackage : String? = null,
    var link : String? = null,
    var devKey : String? = null,
    var organic : Boolean? = null,
    @Column(length = 500)
    var banGeo : String? = null,
    var organicLink : String? = null,
    @Column(length = 500)
    var authKey : String? = null,
)

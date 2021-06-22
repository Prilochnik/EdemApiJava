package com.example.edemapi.entities

import javax.persistence.*

@Entity
@Table(name = "black_net")
data class BlackNetEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private var id : Long? = null,
        var net : String? = null,
        var cause : String? = null
)

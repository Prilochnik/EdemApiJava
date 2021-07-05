package com.example.edemapi.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*

@Entity
@Table(name = "pushes")
data class PushEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private val id : Long? = null,
        @Column(length = 1000)
        var title : String? = null,
        @Column(length = 1000)
        var body : String? = null,

        @Embedded
        var time : Time? = null,

        var lang : String? = null,

        var geo : String? = null,

        var appPackage : String? = null,

        @OneToMany(mappedBy = "pushId", fetch = FetchType.EAGER)
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property="id")
        @JsonIdentityReference(alwaysAsId = true)

        var users : List<UserEntity>? = null
)

@Embeddable
data class Time(
        var hour : String,
        var minutes : String
)

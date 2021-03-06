package com.example.edemapi.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import lombok.ToString
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id : Long? = null,
        var userId : String? = null,
        var appPackage : String? = null,
        var locale : String? = null,
        var ip : String? = null, //not in req
        @Temporal(TemporalType.TIMESTAMP)
        var date : Date? = null, //not in req
        @Column(length = 500)
        var campaign : String? = null,
        var campaignId : String? = null,
        var afAdset : String? = null,
        var afAdsetId : String? = null,
        var afAd : String? = null,
        @Column(length = 1000)
        var result : String? = null, //not in req
        @Column(length = 1000)
        var pushToken : String? = null,
        var afAdId : String? = null,
        var geo : String? = null, //not in req
        var lang : String? = null,
        @ManyToOne(optional = true, cascade = [CascadeType.ALL])
        @JoinColumn(name = "push")
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
        @JsonIdentityReference(alwaysAsId = true)
        @JsonIgnore
        var pushId : PushEntity? = null, //not in req
        var mediaSource : String? = null,


)
package com.example.edemapi.entities

import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private var id : Long? = null,
        var userId : String? = null,
        var appPackage : String? = null,
        var locale : String? = null,
        var ip : String? = null,
        var date : String? = null,
        var campaign : String? = null,
        var campaignId : String? = null,
        var afAdset : String? = null,
        var afAdsetId : String? = null,
        var afAd : String? = null,
        var result : String? = null,
        var pushToken : String? = null,
        var afAdId : String? = null,
        var geo : String? = null,
        var lang : String? = null,
        var pushId : String? = null,


)
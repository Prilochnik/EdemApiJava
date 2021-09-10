package com.example.edemapi.service

import com.example.edemapi.models.Geo
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class GeoService {

    fun checkGeo(ip : String, banGeoStr : String): Allow {
        val banGeo = banGeoStr.split(",")
        val geo = getGeoByIp(ip)
        geo?.let {
            return Allow(geo, !banGeo.contains(geo.country?.iso))
        }
        return Allow(geo, true)
    }

    fun getGeoByIp(ip : String): Geo? {
        return WebClient.create("https://ru3.sxgeo.city/82J9U")
            .get()
            .uri("json/$ip")
            .retrieve()
            .bodyToMono(Geo::class.java)
            .block()
    }
}

data class Allow(
    val geo : Geo?,
    val allow : Boolean
)

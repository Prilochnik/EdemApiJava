package com.example.edemapi.models

import lombok.NoArgsConstructor

@NoArgsConstructor
data class Geo(
    var city: City? = null,
    var country: Country? = null,
    var created: String? = null,
    var error: String? = null,
    var ip: String? = null,
    var region: Region? = null,
    var request: Int? = null,
    var timestamp: Int? = null
)

data class City(
    var id: Int? = null,
    var lat: Double? = null,
    var lon: Double? = null,
    var name_de: String? = null,
    var name_en: String? = null,
    var name_es: String? = null,
    var name_fr: String? = null,
    var name_it: String? = null,
    var name_pt: String? = null,
    var name_ru: String? = null,
    var okato: String? = null,
    var population: Int? = null,
    var post: String? = null,
    var tel: String? = null,
    var vk: Int? = null
)

data class Country(
    var area: Int? = null,
    var capital_en: String? = null,
    var capital_id: Int? = null,
    var capital_ru: String? = null,
    var continent: String? = null,
    var cur_code: String? = null,
    var id: Int? = null,
    var iso: String? = null,
    var lat: Int? = null,
    var lon: Int? = null,
    var name_de: String? = null,
    var name_en: String? = null,
    var name_es: String? = null,
    var name_fr: String? = null,
    var name_it: String? = null,
    var name_pt: String? = null,
    var name_ru: String? = null,
    var neighbours: String? = null,
    var phone: String? = null,
    var population: Int? = null,
    var timezone: String? = null,
    var utc: Int? = null,
    var vk: Int? = null
)

data class Region(
    var auto: String? = null,
    var id: Int? = null,
    var iso: String? = null,
    var lat: Double? = null,
    var lon: Double? = null,
    var name_de: String? = null,
    var name_en: String? = null,
    var name_es: String? = null,
    var name_fr: String? = null,
    var name_it: String? = null,
    var name_pt: String? = null,
    var name_ru: String? = null,
    var okato: String? = null,
    var timezone: String? = null,
    var utc: Int? = null,
    var vk: Int? = null
)
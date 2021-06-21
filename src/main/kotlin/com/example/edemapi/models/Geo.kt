package com.example.edemapi.models

data class Geo(
    val city: City,
    val country: Country,
    val created: String,
    val error: String,
    val ip: String,
    val region: Region,
    val request: Int,
    val timestamp: Int
)

data class City(
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name_de: String,
    val name_en: String,
    val name_es: String,
    val name_fr: String,
    val name_it: String,
    val name_pt: String,
    val name_ru: String,
    val okato: String,
    val population: Int,
    val post: String,
    val tel: String,
    val vk: Int
)

data class Country(
    val area: Int,
    val capital_en: String,
    val capital_id: Int,
    val capital_ru: String,
    val continent: String,
    val cur_code: String,
    val id: Int,
    val iso: String,
    val lat: Int,
    val lon: Int,
    val name_de: String,
    val name_en: String,
    val name_es: String,
    val name_fr: String,
    val name_it: String,
    val name_pt: String,
    val name_ru: String,
    val neighbours: String,
    val phone: String,
    val population: Int,
    val timezone: String,
    val utc: Int,
    val vk: Int
)

data class Region(
    val auto: String,
    val id: Int,
    val iso: String,
    val lat: Double,
    val lon: Double,
    val name_de: String,
    val name_en: String,
    val name_es: String,
    val name_fr: String,
    val name_it: String,
    val name_pt: String,
    val name_ru: String,
    val okato: String,
    val timezone: String,
    val utc: Int,
    val vk: Int
)
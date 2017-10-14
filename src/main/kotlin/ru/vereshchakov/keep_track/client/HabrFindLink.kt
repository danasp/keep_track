package ru.vereshchakov.keep_track.client

import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

/**
 * User: Danila Vereshchakov
 * Date: 14.10.2017
 */

class HabrFindLink: FindLink {

    val url = "https://habrahabr.ru/all/"
    val toppics: List<String> = listOf("kotlin")

    override fun find(): List<String> {
        val rest: RestTemplate = RestTemplate()
        var res = rest.getForObject<String>(url, String::class.java)

        return listOf("")
    }
}
package com.fioalpha.platform.network.model

import java.util.Date

data class Character(
    val id: Int?,
    val name: String,
    val description: String?,
    val modified: Date?,
    val resourceURI: String,
    val urls: List<Url>,
    val thumbnail: Image,
)
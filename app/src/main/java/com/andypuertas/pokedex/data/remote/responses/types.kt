package com.andypuertas.pokedex.data.remote.responses

data class types(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<ResultX>
)
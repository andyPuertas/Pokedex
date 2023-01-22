package com.andypuertas.pokedex.data.models

import com.andypuertas.pokedex.data.remote.responses.Type

data class PokedexListEntry(
    val pokemonName: String,
    val imageUrl: String,
    val number: Int,
    var type: List<Type> = emptyList()
)
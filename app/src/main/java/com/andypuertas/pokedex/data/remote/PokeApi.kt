package com.andypuertas.pokedex.data.remote

import com.andypuertas.pokedex.data.remote.responses.Pokemon
import com.andypuertas.pokedex.data.remote.responses.PokemonList
import com.andypuertas.pokedex.data.remote.responses.types
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ): Pokemon

    @GET("type")
    suspend fun getPokemonTypes(
    ): types
}
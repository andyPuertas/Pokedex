package com.andypuertas.pokedex.repository

import com.andypuertas.pokedex.R
import com.andypuertas.pokedex.data.remote.PokeApi
import com.andypuertas.pokedex.data.remote.responses.Pokemon
import com.andypuertas.pokedex.data.remote.responses.PokemonList
import com.andypuertas.pokedex.data.remote.responses.types
import com.andypuertas.pokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch(e: Exception) {
            return Resource.Error("Ha ocurrido un error desconocido.")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(pokemonName)
        } catch(e: Exception) {
            return Resource.Error("Ha ocurrido un error desconocido.")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemontypes(): Resource<types> {
        val response = try {
            api.getPokemonTypes()
        } catch(e: Exception) {
            return Resource.Error("Ha ocurrido un error desconocido.")
        }
        return Resource.Success(response)
    }
}
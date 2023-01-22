package com.andypuertas.pokedex.pokemoninfo

import androidx.lifecycle.ViewModel
import com.andypuertas.pokedex.data.remote.responses.Pokemon
import com.andypuertas.pokedex.repository.PokemonRepository
import com.andypuertas.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }
}
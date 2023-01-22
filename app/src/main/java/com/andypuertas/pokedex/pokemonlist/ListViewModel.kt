package com.andypuertas.pokedex.pokemonlist

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.andypuertas.pokedex.data.models.PokedexListEntry
import com.andypuertas.pokedex.data.models.PokedexTypesEntry
import com.andypuertas.pokedex.data.remote.responses.Pokemon
import com.andypuertas.pokedex.data.remote.responses.Type
import com.andypuertas.pokedex.repository.PokemonRepository
import com.andypuertas.pokedex.util.Constants.PAGE_SIZE
import com.andypuertas.pokedex.util.Resource
import com.google.accompanist.coil.CoilImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import androidx.compose.runtime.produceState as produceState1

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private var curPage = 0

    var pokemonList = mutableStateOf<List<PokedexListEntry>>(listOf())
    var pokemonTypes = mutableStateOf<List<PokedexTypesEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    private var cachePokemonList = listOf<PokedexListEntry>()
    private var isSearchStarting = true
    private var isFilterStarting = true
    var isSearching = mutableStateOf(false)
    var isFilter = mutableStateOf(false)

    init {
        loadPokemonPaginated()
        loadPokemonAlltypes()
    }

    fun searchPokemonList(query: String) {
        val listToSearch = if (isSearchStarting) {
            pokemonList.value
        } else {
            cachePokemonList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                pokemonList.value = cachePokemonList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.pokemonName.contains(query.trim(), ignoreCase = true) ||
                        it.number.toString() == query.trim() ||
                        String.format("%03d", it.number).contains(query.trim())
            }
            if (isSearchStarting) {
                cachePokemonList = pokemonList.value
                isSearchStarting = false
            }
            pokemonList.value = results
            isSearching.value = true
        }
    }

    fun filterByTypePokemonList(typeFilter: String) {
        val listToFilter = pokemonList.value
        viewModelScope.launch(Dispatchers.Default) {
            val results = listToFilter.filter {
                it.type.filter { it2 -> it2.type.name.contains(typeFilter) }.size == 1
            }
            pokemonList.value = results
        }
    }

    fun loadPokemonPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getPokemonList(PAGE_SIZE, curPage * PAGE_SIZE)
            when (result) {
                is Resource.Success -> {
                    endReached.value = curPage * PAGE_SIZE >= result.data!!.count
                    val pokedexEntries = result.data.results.mapIndexed { index, entry ->
                        val number = if (entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val url =
                            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokedexListEntry(entry.name.capitalize(Locale.ROOT), url, number.toInt())
                    }
                    curPage++

                    loadError.value = ""
                    isLoading.value = false
                    pokemonList.value += pokedexEntries
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

    fun loadPokemonAlltypes() {
        viewModelScope.launch {
            isLoading.value = true
            val resultPokemonTypes = repository.getPokemontypes()
            when (resultPokemonTypes) {
                is Resource.Success -> {
                    resultPokemonTypes.data!!.count > 0
                    val pokedexTypesEntries =
                        resultPokemonTypes.data.results.mapIndexed { index, entry ->
                            val nameType = entry.name
                            PokedexTypesEntry(nameType)
                        }

                    loadError.value = ""
                    isLoading.value = false
                    pokemonTypes.value += pokedexTypesEntries
                }
                is Resource.Error -> {
                    loadError.value = resultPokemonTypes.message!!
                    isLoading.value = false
                }
            }
        }
    }
}
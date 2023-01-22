package com.andypuertas.pokedex.util

import androidx.compose.ui.graphics.Color
import com.andypuertas.pokedex.data.remote.responses.Type
import com.andypuertas.pokedex.data.remote.responses.Stat
import com.andypuertas.pokedex.ui.theme.*
import java.util.*

fun parseTypeToColor(type: Type): Color {
    return when (type.type.name.toLowerCase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        "unknown" -> TypeUnknown
        "shadow" -> TypeShadow
        else -> Color.Black
    }
}

fun parseTypesToColor(name: String): Color {
    return when (name.toLowerCase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        "unknown" -> TypeUnknown
        "shadow" -> TypeShadow
        else -> Color.Black
    }
}

fun parseTypeSpanish(name: String): String {
    return when (name.toLowerCase(Locale.ROOT)) {
        "normal" -> "Normal"
        "fire" -> "Fuego"
        "water" -> "Agua"
        "electric" -> "Eléctrico"
        "grass" -> "Planta"
        "ice" -> "Hielo"
        "fighting" -> "Lucha"
        "poison" -> "Veneno"
        "ground" -> "Tierra"
        "flying" -> "Volador"
        "psychic" -> "Psíquico"
        "bug" -> "Bicho"
        "rock" -> "Roca"
        "ghost" -> "Fantasma"
        "dragon" -> "Dragón"
        "dark" -> "Siniestro"
        "steel" -> "Acero"
        "fairy" -> "Hada"
        "unknown" -> "Desconocido"
        "shadow" -> "Sombra"
        else -> "Normal"
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when (stat.stat.name.toLowerCase()) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when (stat.stat.name.toLowerCase()) {
        "hp" -> "HP"
        "attack" -> "Ataque"
        "defense" -> "Defensa"
        "special-attack" -> "Ataque especial"
        "special-defense" -> "Defensa especial"
        "speed" -> "Velocidad"
        else -> ""
    }
}
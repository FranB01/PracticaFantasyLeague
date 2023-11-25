package com.example.practicafantasyleague.datos

import java.util.Date

data class PaisFantasy(
    var pais: Pais,
    var alianza: Alianza,
    //var continente : List<Continente>,
    var equipamiento: List<Equipamiento>,
    var batallasGanadas: Int,
    var batallaVS: String,
    var fechaGuerra: Date,
)

enum class Alianza {
    OCCIDENTE {
        override fun toString(): String {
            return "Occidente"
        }
    },
    BLOQUE_RUSO {
        override fun toString(): String {
            return "Bloque ruso"
        }
    },
    BLOQUE_CHINO {
        override fun toString(): String {
            return "Bloque chino"
        }
    },
    INDEPENDIENTE {
        override fun toString(): String {
            return "Independiente"
        }
    },
}

enum class Equipamiento {
    INFANTERIA {
        override fun toString(): String {
            return "Infanteria"
        }
    },
    TANQUES {
        override fun toString(): String {
            return "Tanques"
        }
    },
    APC {
        override fun toString(): String {
            return "APC"
        }
    },
    CAZAS {
        override fun toString(): String {
            return "Cazas"
        }
    },
    BARCOS {
        override fun toString(): String {
            return "Barcos"
        }
    },
}
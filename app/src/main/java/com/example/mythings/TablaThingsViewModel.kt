package com.example.mythings

import androidx.lifecycle.ViewModel
import java.util.*

class TablaThingsViewModel: ViewModel() {
    val inventario:MutableList<Cosa> = mutableListOf<Cosa>()
    val nombres:Array<String> = arrayOf("Alcohol","Audífono", "Teléfono")
    val adjetivos:Array<String> = arrayOf("feo","grande","negro")
    init {
        for(i in 0 until 100) {
            val cosa = Cosa()
            val nombreRandom = nombres.random()
            val adjetivorandom = adjetivos.random()
            val precioRandom = Random().nextInt(100)
            cosa.nombreC = "$nombreRandom $adjetivorandom"
            cosa.valorP = precioRandom
            inventario += cosa
        }
    }
}
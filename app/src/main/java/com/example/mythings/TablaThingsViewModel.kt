package com.example.mythings

import androidx.lifecycle.ViewModel
import java.io.File
import java.io.FileOutputStream
import java.util.*

class TablaThingsViewModel: ViewModel() {
    val inventario:MutableList<Cosa> = mutableListOf()
    private val nombres:Array<String> = arrayOf("Alcohol","Audífono", "Teléfono")
    private val adjetivos:Array<String> = arrayOf("feo","grande","negro")
    val file = File("inventarioGuardado.txt")

    init {
        if(file.exists()){

        }else{
            for(i in 0 until 100) {
                val cosa = Cosa()
                val nombreRandom = nombres.random()
                val adjetivorandom = adjetivos.random()
                val precioRandom = Random().nextInt(1000)
                cosa.nombreC = "$nombreRandom $adjetivorandom"
                cosa.valorP = precioRandom
                inventario += cosa
            }
        }
    }

    fun deleteThing(posicion: Int){
        inventario.removeAt(posicion)
    }
}
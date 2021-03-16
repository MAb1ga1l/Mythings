 package com.example.mythings


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

 private const val TAG = "MainActivity"
 class MainActivity : AppCompatActivity() , TablaThingsFragment.Callback{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentoActual=supportFragmentManager.findFragmentById(R.id.contenedor_fragment)
        //lo que sigue es solo para asegurar que se creo el frgmento correctamente
        if(fragmentoActual == null){
            // en caso de no estar creado, por ejemplo en la primera vez que se corre entonces se crea el fragmento
            //val fragment=cosaFragment()
            val fragment = TablaThingsFragment.nuevaInstancia()
            supportFragmentManager.beginTransaction().add(R.id.contenedor_fragment, fragment).commit()
        }
    }

    override fun onThingSeleccionada(cosa: Cosa) {
        Log.d(TAG,"MainActivyty.onCosaSeleccionada recibió${cosa.nombreC} ${cosa.valorP}")
        val fragment = cosaFragment.nuevaInstancia(cosa)
        //fragment.cosaAMostrar(cosa)
        //vamos a reemplazar pero el addToBackStack se pone para exista la interacción entre los dos fragmentos
        supportFragmentManager.beginTransaction().replace(R.id.contenedor_fragment,fragment).addToBackStack(null).commit()
    }
}
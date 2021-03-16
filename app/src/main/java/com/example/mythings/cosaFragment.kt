package com.example.mythings

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*

private const val TAG="cosaFragment"
class cosaFragment: Fragment() {
    private lateinit var cosa: Cosa
    private lateinit var campoNombre: EditText
    private lateinit var campoPrecio: EditText
    private lateinit var campoSerie: EditText
    private lateinit var LabelFecha: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cosa = Cosa()
        cosa = arguments?.getParcelable("COSA_ENVIADA")!!
        Log.d(TAG, "Se recibio ${cosa.nombreC} de $${cosa.valorP}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.cosa_fragment, container, false)
        campoNombre = vista.findViewById(R.id.campoNombre)
        campoPrecio = vista.findViewById(R.id.campoPrecio)
        campoSerie = vista.findViewById(R.id.campoSerie)
        LabelFecha = vista.findViewById(R.id.textView4)
        campoNombre.setText(cosa.nombreC)
        campoPrecio.setText(cosa.valorP.toString())
        LabelFecha.text = cosa.fechaCreacion.toString()
        campoSerie.setText(cosa.numSerie.toString())
        return vista
    }

    override fun onStart() {
        super.onStart()
        val observador= object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Por si se quiere hacer alguna validación antes de cambiar el texto
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.hashCode() == campoNombre.text.hashCode()){
                    cosa.nombreC = s.toString()
                }
                if(s.hashCode() == campoPrecio.text.hashCode()){
                    cosa.valorP = s.toString().toInt()
                }
                if(s.hashCode() == campoSerie.text.hashCode()){
                    cosa.numSerie == UUID.fromString(s.toString())
                }
            }
        }
        campoNombre.addTextChangedListener(observador)
        campoPrecio.addTextChangedListener(observador)
        campoSerie.addTextChangedListener(observador)
    }

    /*fun cosaAMostrar(cosa: Cosa){
        Log.d(TAG,"ME pasaron un ${cosa.nombreC} de ${cosa.valorP}")
        this.cosa = cosa
    }*/

    companion object{
        fun nuevaInstancia(cosa: Cosa) : cosaFragment{
            val argumentos = Bundle().apply {
                //es un put para objetos más complejos
                putParcelable("COSA_ENVIADA", cosa)
            }
            return cosaFragment().apply {
                arguments = argumentos
            }
        }
    }
}
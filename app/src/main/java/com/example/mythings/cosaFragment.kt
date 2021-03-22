package com.example.mythings

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.util.*

private const val TAG="CosaFragment"
class CosaFragment: Fragment() {
    private lateinit var cosa: Cosa
    private lateinit var campoNombre: EditText
    private lateinit var campoPrecio: EditText
    private lateinit var campoSerie: EditText
    private lateinit var labelFecha: TextView
    private lateinit var botonFecha: Button
    private lateinit var contexto: Context

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
        labelFecha = vista.findViewById(R.id.textView4)
        campoNombre.setText(cosa.nombreC)
        campoPrecio.setText(cosa.valorP.toString())
        labelFecha.text = cosa.fechaCreacion.toString()
        campoSerie.setText(cosa.numSerie.toString())
        botonFecha = vista.findViewById(R.id.button_cambioFecha)
        return vista
    }

    @SuppressLint("SetTextI18n")
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
                    //Para asegurar que el campo de nombre se llene
                    if(s != null){
                        if(s.isEmpty()) Toast.makeText(context,"Falta llenar el nombre",Toast.LENGTH_SHORT).show()
                    }
                    cosa.nombreC = s.toString()
                }
                if(s.hashCode() == campoPrecio.text.hashCode()){
                    if(s != null){
                        if(s.isEmpty()) cosa.valorP = 0
                    }else{
                        cosa.valorP = s.toString().toInt()
                    }
                }
                if(s.hashCode() == campoSerie.text.hashCode()){
                    cosa.numSerie = UUID.fromString(s.toString())
                }
            }
        }

        campoNombre.addTextChangedListener(observador)
        campoPrecio.addTextChangedListener(observador)
        campoSerie.addTextChangedListener(observador)

        // Para realizar el DatePicker
        val fecha = Calendar.getInstance()
        val anio = fecha.get(Calendar.YEAR)
        val mes = fecha.get(Calendar.YEAR)
        val dia = fecha.get(Calendar.YEAR)

        botonFecha.setOnClickListener {
            val fechafinal = DatePickerDialog(contexto  , { view, year, monthOfYear, dayOfMonth ->
                val calMes= (monthOfYear + 1)
                val meses= calMes.toString()

                labelFecha.text = "$dayOfMonth/$meses/$year"
            }, anio, mes, dia)
            fechafinal.datePicker.maxDate = System.currentTimeMillis()
            fechafinal.show()
        }

    }

    /*fun cosaAMostrar(cosa: Cosa){
        Log.d(TAG,"ME pasaron un ${cosa.nombreC} de ${cosa.valorP}")
        this.cosa = cosa
    }*/

    companion object{
        fun nuevaInstancia(cosa: Cosa) : CosaFragment{
            val argumentos = Bundle().apply {
                //es un put para objetos más complejos
                putParcelable("COSA_ENVIADA", cosa)
            }
            return CosaFragment().apply {
                arguments = argumentos
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contexto = context
    }
}
package com.example.mythings

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import javax.security.auth.callback.Callback

private const val TAG = "TablaThingsFragment"
class TablaThingsFragment : Fragment(){

    //para crear la liga al layout
    private lateinit var tablaRecycleView:RecyclerView
    private var adapter : ThingAdapter?=null

    private val inventarioTabla : TablaThingsViewModel by lazy {
        ViewModelProvider(this).get(TablaThingsViewModel::class.java)
    }

    interface Callback{
        fun onThingSeleccionada(cosa: Cosa)
    }

    private var callback: Callback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total de cosas: ${inventarioTabla.inventario.size}")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //siempre y cuando se implemente el método Callback se creará el contexto
        callback = context as Callback?
    }

    companion object{
        fun nuevaInstancia():TablaThingsFragment{
            return TablaThingsFragment()
        }
    }

    private  fun poblarTabla(){
        adapter = ThingAdapter(inventarioTabla.inventario)
        tablaRecycleView.adapter = adapter

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val vista = inflater.inflate(R.layout.tabla_things_fragment,container,false)
        tablaRecycleView = vista.findViewById(R.id.tabla_recycler_view) as RecyclerView
        //el linear Layout para ir generando hacía abajo de forma lineal lo que se va creando de forma automatica
        tablaRecycleView.layoutManager = LinearLayoutManager(context)
        //así mismo este layout nos permitirá hacer un scroll
        poblarTabla()
        return vista
    }

    private inner class ThingHolder(vista:View):RecyclerView.ViewHolder(vista), View.OnClickListener{
        //Aqui se realizará el binding entre la vista y el RecyclerView
        private lateinit var cosa:Cosa
        val nombreTextView: TextView = itemView.findViewById(R.id.nombre_thing)
        val precioTextView: TextView = itemView.findViewById(R.id.precio_thing)

        init {
            itemView.setOnClickListener(this)
        }

        fun holderBinding(cosa: Cosa){
            this.cosa = cosa
            nombreTextView.text = cosa.nombreC
            precioTextView.text = "${cosa.valorP}"
        }

        override fun onClick(v: View?) {
            //Toast.makeText(context,"Seleccionaste ${cosa.nombreC}",Toast.LENGTH_SHORT).show()
            callback?.onThingSeleccionada(cosa)
        }
    }

    private inner class ThingAdapter(var inventario:List<Cosa>) : RecyclerView.Adapter<ThingHolder>(){
        //Esta clase funcionará como adpatdor de tipo Holder
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThingHolder {
            //Primero se "crea" o bien se infla la celda
            val vista = layoutInflater.inflate(R.layout.celda_layout,parent,false)
            return ThingHolder(vista)
        }

        //esto nos va a decir cuantas veces se tiene que repetir el proceso
        //En este caso deforma sencilla se iguala al tamaño del inventario
        override fun getItemCount() = inventario.size


        override fun onBindViewHolder(holder: ThingHolder, position: Int) {
            //Se buscará cada cosa con su debida información para poblar la celda
            val cosa = inventario[position]
            holder.apply {
                holder.holderBinding(cosa)
            }
        }

    }

}
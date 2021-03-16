package com.example.mythings

import android.os.Parcel
import android.os.Parcelable
import java.util.*

//Esta clase solo va a tener de momento la definición de lo que es una cosa
//esta clase dato, nos permite definir sin tener que implementar
/*data class Cosa(var nombreC: String = "",
                var valorP: Int = 0,
                val fechaCreacion: Date = Date(),
                //Un número único de identificación
                val numSerie: UUID = UUID.randomUUID())*/

class Cosa() : Parcelable {

    var nombreC: String = ""
    var valorP: Int = 0
    var fechaCreacion: Date = Date()
    var numSerie: UUID = UUID.randomUUID()

    constructor(parcel: Parcel) : this() {
        //Aquí se especifica cómo se desempaqueta
        nombreC = parcel.readString().toString()
        valorP = parcel.readInt()
        fechaCreacion = parcel.readSerializable() as Date
        numSerie = parcel.readSerializable() as UUID
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        //Aquí se especifica cómo se tiene que empaquetar
        parcel.writeString(nombreC)
        parcel.writeInt(valorP)
        parcel.writeSerializable(fechaCreacion)
        parcel.writeSerializable(numSerie)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cosa> {
        override fun createFromParcel(parcel: Parcel): Cosa {
            return Cosa(parcel)
        }

        override fun newArray(size: Int): Array<Cosa?> {
            return arrayOfNulls(size)
        }
    }

}
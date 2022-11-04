package joseoliva.com.happburguer.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import joseoliva.com.happburguer.R
import joseoliva.com.happburguer.bbdd.BurguerPedida

class BurguerPedidoViewHolder(view: View): RecyclerView.ViewHolder(view) {

    //obtengo las vistas que voy a tener que rellenar en cada item
    val foto = view.findViewById<ImageView>(R.id.ivfotoburguer)
    val precio = view.findViewById<TextView>(R.id.tvprecio)
    val nombre = view.findViewById<TextView>(R.id.tvnombre)
    val seleccion = view.findViewById<TextView>(R.id.tvseleccion)
    val btnborrar = view.findViewById<ImageView>(R.id.btnborrar)

    fun render(
        burguerpedidamodel: BurguerPedida,
        onClickDelete: (BurguerPedida) -> Unit){

        //relleno las vistas con los datos que obtengo de cada item que recorro
        foto.setImageResource(burguerpedidamodel.foto)
        precio.text = burguerpedidamodel.precio.toString() + "€"
        nombre.text = burguerpedidamodel.nombre
        seleccion.text = burguerpedidamodel.personalizacion

        //doy funcionalidad al boton de borrar
        btnborrar.setOnClickListener {
            onClickDelete(burguerpedidamodel)
        }

    }

}
package joseoliva.com.happburguer.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
    val btnmenos = view.findViewById<ImageView>(R.id.btnmenos)
    val btnmas = view.findViewById<ImageView>(R.id.btnmas)
    val counter = view.findViewById<TextView>(R.id.tvcontador)
    //y me creo una var para el contador
    var contador = 0

    fun render(
        burguerpedidamodel: BurguerPedida,
        onClickDelete: (BurguerPedida) -> Unit,
        onCounter:(BurguerPedida) -> Unit){
        //relleno las vistas con los datos que obtengo de cada item que recorro
        foto.setImageResource(burguerpedidamodel.foto)
        precio.text = burguerpedidamodel.precio.toString() + "€"
        nombre.text = burguerpedidamodel.nombre
        seleccion.text = burguerpedidamodel.personalizacion
        counter.text = burguerpedidamodel.cantidad.toString()

        //doy funcionalidad al boton de borrar y al de home
        btnborrar.setOnClickListener {
            onClickDelete(burguerpedidamodel)
        }

        //doy funcionalidad a los botones - +
        btnmenos.setOnClickListener {
            if(burguerpedidamodel.cantidad>1){
                burguerpedidamodel.cantidad--
                counter.text = burguerpedidamodel.cantidad.toString()
            }
            onCounter(burguerpedidamodel)
        }

        btnmas.setOnClickListener {
            if(burguerpedidamodel.cantidad<9){
                burguerpedidamodel.cantidad++
                counter.text = burguerpedidamodel.cantidad.toString()
            }
            onCounter(burguerpedidamodel)
        }

    }

}
package joseoliva.com.happburguer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import joseoliva.com.happburguer.R
import joseoliva.com.happburguer.modeloviewpager.BurguerModelViewPager

class BurguerItemsAdapter(
    //recibo la lista de las burguers y dos funciones lambda que se ejecutaran en el main activity
    private val listaBurguers: List<BurguerModelViewPager>,
    private val onClickEditar: (BurguerModelViewPager) -> Unit,
    private val onClickCarrito: (BurguerModelViewPager) -> Unit) :
    RecyclerView.Adapter<BurguerItemsAdapter.BurguerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BurguerViewHolder {
        return BurguerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.burguer_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BurguerViewHolder, position: Int) {
        //pasamos cada elemento de la lista al ViewHolder
        holder.render(listaBurguers[position],onClickEditar,onClickCarrito)
    }

    override fun getItemCount(): Int {
        return listaBurguers.size
    }

    //como es un holder sencillo, creo la clase dentro de esta misma
    inner class BurguerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //inicializo las vistas del xml
        val fotoburguer = view.findViewById<ImageView>(R.id.fotoburguer)
        val precio = view.findViewById<TextView>(R.id.tvprecio)
        val nombre = view.findViewById<TextView>(R.id.tvnombre)
        val descripcion = view.findViewById<TextView>(R.id.tvdescripcion)
        val ingrediente1 = view.findViewById<TextView>(R.id.tvingrediente1)
        val ingrediente2 = view.findViewById<TextView>(R.id.tvingrediente2)
        val ingrediente3 = view.findViewById<TextView>(R.id.tvingrediente3)
        val ingrediente4 = view.findViewById<TextView>(R.id.tvingrediente4)
        val ingrediente5 = view.findViewById<TextView>(R.id.tvingrediente5)

        val btneditar = view.findViewById<ImageView>(R.id.btneditar)
        val btncarrito = view.findViewById<ImageView>(R.id.btncarrito)

        fun render(
            burgueritem: BurguerModelViewPager,
            onClickEditar: (BurguerModelViewPager) -> Unit,
            onClickCarrito: (BurguerModelViewPager) -> Unit
        ) {
            //relleno las vistas con los datos que traigo de cada item de la lista
            fotoburguer.setImageResource(burgueritem.fotoburguer)
            precio.text = burgueritem.precio
            nombre.text = burgueritem.nombre
            descripcion.text = burgueritem.descripcion
            ingrediente1.text = burgueritem.ingrediente1
            ingrediente2.text = burgueritem.ingrediente2
            ingrediente3.text = burgueritem.ingrediente3
            ingrediente4.text = burgueritem.ingrediente4
            ingrediente5.text = burgueritem.ingrediente5

            btneditar.setOnClickListener {
                onClickEditar(burgueritem)
            }

            btncarrito.setOnClickListener {
                onClickCarrito(burgueritem)
            }
        }
    }
}
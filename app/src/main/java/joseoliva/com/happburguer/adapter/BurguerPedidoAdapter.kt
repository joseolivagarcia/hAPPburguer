package joseoliva.com.happburguer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import joseoliva.com.happburguer.R
import joseoliva.com.happburguer.bbdd.BurguerPedida

class BurguerPedidoAdapter(
    val onClickDelete: (BurguerPedida) -> Unit): RecyclerView.Adapter<BurguerPedidoViewHolder>() {

    //creo una var de lista donde guardare todos las burguers pedidas
    private var allBurguersPedidas = ArrayList<BurguerPedida>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BurguerPedidoViewHolder {
        val layoutinflater = LayoutInflater.from(parent.context)
        return BurguerPedidoViewHolder(layoutinflater.inflate(R.layout.item_burguer_pedida,parent,false))
    }

    override fun onBindViewHolder(holder: BurguerPedidoViewHolder, position: Int) {
        val item = allBurguersPedidas[position]
        holder.render(item,onClickDelete)
    }

    override fun getItemCount(): Int {
        return allBurguersPedidas.size
    }

    fun updateList(newList: List<BurguerPedida>){
        allBurguersPedidas.clear()
        allBurguersPedidas.addAll(newList)
        notifyDataSetChanged()
    }

}
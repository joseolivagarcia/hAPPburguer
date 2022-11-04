package joseoliva.com.happburguer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import joseoliva.com.happburguer.adapter.BurguerPedidoAdapter
import joseoliva.com.happburguer.bbdd.BurguerPedida
import joseoliva.com.happburguer.databinding.ActivityCarritoBinding
import joseoliva.com.happburguer.viewmodel.BurguerViewModel

class CarritoActivity : AppCompatActivity() {

    lateinit var binding: ActivityCarritoBinding
    lateinit var viewModel: BurguerViewModel
    lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inicio mi toolbar y sobreescribo los metodos necesarios (al final)
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        recyclerview = binding.rvpedidos
        //inicializamos el manager del recycler
        recyclerview.layoutManager = LinearLayoutManager(this)
        //inicializamos el adapter y le pasamos como parametros el contexto y las interface que necesita
        val burguerRVAdapter = BurguerPedidoAdapter(onClickDelete = {burguerPedida -> onItemDelete(burguerPedida)})
        //ponemos el adapter que acabamos de referenciar al recyclerview
        recyclerview.adapter = burguerRVAdapter

        //inicializamos el viewmodel con un provider y le pasamos nuestra clase de PostitViewModel
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(BurguerViewModel::class.java)

        //Llamamos a la lista de postit del viewModel para observar los cambios que haya en la lista
        //lo puedo "observar" porque es un LiveData
        viewModel.listaburguers.observe(this, Observer { list -> list?.let{
            //actualizamos la lista
            burguerRVAdapter.updateList(it)
            binding.tvpreciototal.text = viewModel.preciototalpedido.toString()
        }
        })

        //funcionalidad del boton home
        binding.home.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun onItemDelete(burguerPedida: BurguerPedida) {
        val dialog = AlertDialog.Builder(this)
            .setMessage("Seguro de eliminar este manjar?")
            .setNegativeButton("NO"){
                    view, _ -> view.dismiss()
            }
            .setPositiveButton("SI"){
                    view,_ -> view.dismiss()
                viewModel.deleteburguer(burguerPedida)
            }
            .setCancelable(false)
            .create()

        dialog.show()
    }

    //para poner menus en el toolbar sobreescribo estos dos metodos
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mimenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //para indicar una accion al pulsar un item del menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                return  true
            }
            R.id.pedidos -> {
                val intent = Intent(this,CarritoActivity::class.java)
                startActivity(intent)
                return  true
            }
            R.id.pagar -> {
                val intent = Intent(this,CarritoActivity::class.java)
                startActivity(intent)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
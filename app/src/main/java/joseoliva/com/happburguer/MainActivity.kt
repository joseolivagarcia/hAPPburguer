package joseoliva.com.happburguer

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import io.github.muddz.styleabletoast.StyleableToast
import joseoliva.com.happburguer.adapter.BurguerItemsAdapter
import joseoliva.com.happburguer.bbdd.BurguerPedida
import joseoliva.com.happburguer.databinding.ActivityMainBinding
import joseoliva.com.happburguer.modeloviewpager.BurguerModelViewPager
import joseoliva.com.happburguer.provider.BurguersProvider
import joseoliva.com.happburguer.viewmodel.BurguerViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var burguerAdapter: BurguerItemsAdapter
    var burguerlistppal: MutableList<BurguerModelViewPager> = BurguersProvider.burguersListViewpager.toMutableList()
    lateinit var viewModel: BurguerViewModel
    var id: Int = -1 //le doy este valor porque luego usare el real

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inicio mi toolbar y sobreescribo los metodos necesarios (al final)
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        //inicializamos el viewmodel con un provider y le pasamos nuestra clase de BurguerViewModel
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(BurguerViewModel::class.java)

        setBurguersViewpager() //llamo al metodo que pone los items en el viewpager(con el adapter)


    }

    private fun setBurguersViewpager() {
        //inizializo el adapter y le paso una lista que relleno manualmente con las burguers que quiera mostrar
        //y le paso las funciones a ejecutar cuando pulse en editar o en carrito o en tramitar pedidos
        burguerAdapter = BurguerItemsAdapter(burguerlistppal,
        onClickEditar = {burguermodel -> onItemEdit(burguermodel)},
        onClickCarrito = {burguermodel -> onItemCarrito(burguermodel)},
        onClickPedidos = {onItemPedidos()},
        onClickPagar = {onItemPagar()})
        //inicializo el viewPager y le paso el adapter
        val burguersViewPager = findViewById<ViewPager2>(R.id.vp2)
        burguersViewPager.adapter = burguerAdapter
    }

    private fun onItemPagar() {
        //implementando la dependencia StyleableToast creamos Toast personalizados
        StyleableToast
            .Builder(this)
            .text("Para pagar accede desde el carrito de la compra")
            .textColor(Color.WHITE)
            .backgroundColor(Color.parseColor("#592503"))
            .stroke(3, Color.parseColor("#FFFFFFFF"))
            .iconStart(R.drawable.ic_carro)
            .iconEnd(R.drawable.ic_carro)
            .show()
    }

    private fun onItemPedidos() {
        val intent = Intent(this,CarritoActivity::class.java)
        startActivity(intent)

    }

    private fun onItemCarrito(burguermodel: BurguerModelViewPager) {
        //si añado la burguer desde aqui quiere decir que va completa
        val personalizacion = "Completa"
        val newBurguerPedida = BurguerPedida(
            burguermodel.fotoburguer,
            burguermodel.nombre,
            burguermodel.precio.toInt(),
            personalizacion,
            1
        )
        viewModel.insertburguer(newBurguerPedida)
        //implementando la dependencia StyleableToast creamos Toast personalizados
        StyleableToast
            .Builder(this)
            .text("Has añadido una ${burguermodel.nombre} al carrito")
            .textColor(Color.WHITE)
            .backgroundColor(Color.parseColor("#592503"))
            .stroke(3, Color.parseColor("#FFFFFFFF"))
            .iconStart(R.drawable.burguertoast)
            .iconEnd(R.drawable.burguertoast)
            .show()
    }

    private fun onItemEdit(burguermodel: BurguerModelViewPager) {
        val intent = Intent(this,PersonalizarActivity::class.java)
        intent.putExtra("foto",burguermodel.fotoburguer)
        intent.putExtra("precio",burguermodel.precio)
        intent.putExtra("nombre",burguermodel.nombre)
        intent.putExtra("ingrediente1",burguermodel.ingrediente1)
        intent.putExtra("ingrediente2",burguermodel.ingrediente2)
        intent.putExtra("ingrediente3",burguermodel.ingrediente3)
        intent.putExtra("ingrediente4",burguermodel.ingrediente4)
        intent.putExtra("ingrediente5",burguermodel.ingrediente5)
        startActivity(intent)
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
                val intent = Intent(this,PrimeraActivity::class.java)
                startActivity(intent)
                return  true
            }
            R.id.pedidos -> {
                val intent = Intent(this,CarritoActivity::class.java)
                startActivity(intent)
                return  true
            }
            R.id.pagar -> {
                //implementando la dependencia StyleableToast creamos Toast personalizados
                StyleableToast
                    .Builder(this)
                    .text("Para pagar accede desde el carrito de la compra")
                    .textColor(Color.WHITE)
                    .backgroundColor(Color.parseColor("#592503"))
                    .stroke(3, Color.parseColor("#FFFFFFFF"))
                    .iconStart(R.drawable.ic_carro)
                    .iconEnd(R.drawable.ic_carro)
                    .show()
                return  true
            }

        }
        return super.onOptionsItemSelected(item)
    }

}
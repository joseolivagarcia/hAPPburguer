package joseoliva.com.happburguer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import joseoliva.com.happburguer.adapter.BurguerItemsAdapter
import joseoliva.com.happburguer.databinding.ActivityMainBinding
import joseoliva.com.happburguer.modeloviewpager.BurguerModelViewPager
import joseoliva.com.happburguer.provider.BurguersProvider

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var burguerAdapter: BurguerItemsAdapter
    var burguerlistppal: MutableList<BurguerModelViewPager> = BurguersProvider.burguersListViewpager.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inicio mi toolbar y sobreescribo los metodos necesarios (al final)
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        setBurguersViewpager() //llamo al metodo que pone los items en el viewpager(con el adapter)


    }

    private fun setBurguersViewpager() {
        //inizializo el adapter y le paso una lista que relleno manualmente con las burguers que quiera mostrar
        //y le paso las dos funciones a ejecutar cuando pulse en editar o en carrito
        burguerAdapter = BurguerItemsAdapter(burguerlistppal,
        onClickEditar = {burguermodel -> onItemEdit(burguermodel)},
        onClickCarrito = {burguermodel -> onItemCarrito(burguermodel)})
        //inicializo el viewPager y le paso el adapter
        val burguersViewPager = findViewById<ViewPager2>(R.id.vp2)
        burguersViewPager.adapter = burguerAdapter
    }

    private fun onItemCarrito(burguermodel: BurguerModelViewPager) {
        Toast.makeText(this,"Has añadido ${burguermodel.nombre} al carrito",Toast.LENGTH_SHORT).show()
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
        Toast.makeText(this,"Has seleccionado ${burguermodel.nombre}",Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this,"home",Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.carrito -> {
                Toast.makeText(this,"carrito",Toast.LENGTH_SHORT).show()
                return  true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
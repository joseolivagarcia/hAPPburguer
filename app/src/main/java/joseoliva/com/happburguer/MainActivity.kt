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

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var burguerAdapter: BurguerItemsAdapter

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
        burguerAdapter = BurguerItemsAdapter(listOf(
            BurguerModelViewPager(
                fotoburguer = R.drawable.campera,
                precio = "18€",
                nombre = "Campera",
                descripcion = "Magnífica hamburguesa con ingredientes de proximidad para que sientas todo el sabor!",
                ingrediente1 = "Pepino",
                ingrediente2 = "Cebolla",
                ingrediente3 = "Tomate",
                ingrediente4 = "Lechuga",
                ingrediente5 = "Jamón Serrano"
            ),
            BurguerModelViewPager(
                fotoburguer = R.drawable.clasica,
                precio = "15€",
                nombre = "Clásica",
                descripcion = "Magnífica hamburguesa con carne de vacuno 100% y pan brioche. Requetebuena!",
                ingrediente1 = "Pepino",
                ingrediente2 = "Cebolla",
                ingrediente3 = "Tomate",
                ingrediente4 = "Lechuga",
                ingrediente5 = "Queso"
            ),
            BurguerModelViewPager(
                fotoburguer = R.drawable.infantil,
                precio = "12€",
                nombre = "Infantil",
                descripcion = "Magnífica hamburguesa con un tamaño ideal para los más pequeños sin renunciar a todo su sabor!",
                ingrediente1 = "Pepino",
                ingrediente2 = "Cebolla",
                ingrediente3 = "Tomate",
                ingrediente4 = "Lechuga",
                ingrediente5 = "Queso"
            ),
            BurguerModelViewPager(
                fotoburguer = R.drawable.doblecompleta,
                precio = "25€",
                nombre = "Doble Completa",
                descripcion = "Impresionante doble ración de carne de ternera gallega 100%. Solo apta para los más valientes!",
                ingrediente1 = "Pepino",
                ingrediente2 = "Cebolla",
                ingrediente3 = "Tomate",
                ingrediente4 = "Lechuga",
                ingrediente5 = "Huevo frito"
            ),
            BurguerModelViewPager(
                fotoburguer = R.drawable.vegana,
                precio = "20€",
                nombre = "Vegana",
                descripcion = "Magnífica hamburguesa con 'carne' de lentejas y soja para los que quieren disfrutar del sabor sin renunciar a una buena hamburguesa ",
                ingrediente1 = "Pepino",
                ingrediente2 = "Cebolla",
                ingrediente3 = "Tomate",
                ingrediente4 = "Lechuga",
                ingrediente5 = "Calabacín"
            ),
        ),
        onClickEditar = {burguermodel -> onItemEdit(burguermodel)},
        onClickCarrito = {onItemCarrito()})
        //inicializo el viewPager y le paso el adapter
        val burguersViewPager = findViewById<ViewPager2>(R.id.vp2)
        burguersViewPager.adapter = burguerAdapter
    }

    private fun onItemCarrito() {
        Toast.makeText(this,"Has pulsado el carrito",Toast.LENGTH_SHORT).show()
    }

    private fun onItemEdit(burguermodel: BurguerModelViewPager) {
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
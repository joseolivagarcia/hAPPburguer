package joseoliva.com.happburguer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import joseoliva.com.happburguer.databinding.ActivityPagarBinding

class PagarActivity : AppCompatActivity() {

    lateinit var binding: ActivityPagarBinding
    var precioAPagar: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inicio mi toolbar y sobreescribo los metodos necesarios (al final)
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        precioAPagar = intent.getFloatExtra("precio",0f)
        Toast.makeText(this,"voy a pagar $precioAPagar",Toast.LENGTH_SHORT).show()

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
                return  true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    @Override
    override fun onBackPressed() {

    }
}
package joseoliva.com.happburguer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import joseoliva.com.happburguer.databinding.ActivityPersonalizarBinding

class PersonalizarActivity : AppCompatActivity() {

    lateinit var binding: ActivityPersonalizarBinding
    lateinit var foto: ImageView
    lateinit var precio: TextView
    lateinit var nombre: TextView
    lateinit var cbingrediente1: CheckBox
    lateinit var cbingrediente2: CheckBox
    lateinit var cbingrediente3: CheckBox
    lateinit var cbingrediente4: CheckBox
    lateinit var cbingrediente5: CheckBox
    lateinit var btncarrito: ImageView
    //creo las var donde guardare los ingredientes a pasar a la bbdd para el carro
    lateinit var ing1: String
    lateinit var ing2: String
    lateinit var ing3: String
    lateinit var ing4: String
    lateinit var ing5: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalizarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foto = binding.fotoburguer
        precio = binding.tvprecio
        nombre = binding.tvnombre
        cbingrediente1 = binding.cbingrediente1
        cbingrediente2 = binding.cbingrediente2
        cbingrediente3 = binding.cbingrediente3
        cbingrediente4 = binding.cbingrediente4
        cbingrediente5 = binding.cbingrediente5
        btncarrito = binding.btncarrito

        //recojo los datos que vengan de la main activity
        val fotorecibida = intent.getIntExtra("foto",1)
        val preciorecibido = intent.getStringExtra("precio")
        val nombrerecibido = intent.getStringExtra("nombre")
        val ing1recibido = intent.getStringExtra("ingrediente1")
        val ing2recibido = intent.getStringExtra("ingrediente2")
        val ing3recibido = intent.getStringExtra("ingrediente3")
        val ing4recibido = intent.getStringExtra("ingrediente4")
        val ing5recibido = intent.getStringExtra("ingrediente5")

        //y relleno las vistas con los datos
        foto.setImageResource(fotorecibida)
        precio.text = preciorecibido
        nombre.text = nombrerecibido
        cbingrediente1.text = ing1recibido
        cbingrediente2.text = ing2recibido
        cbingrediente3.text = ing3recibido
        cbingrediente4.text = ing4recibido
        cbingrediente5.text = ing5recibido

        //funcionalidad para el boton del carrito
        btncarrito.setOnClickListener {
            if(cbingrediente1.isChecked){
                ing1 = "Con $ing1recibido"
            }else{
                ing1 = "Sin $ing1recibido"
            }
            if(cbingrediente2.isChecked){
                ing2 = "Con $ing2recibido"
            }else{
                ing2 = "Sin $ing2recibido"
            }
            if(cbingrediente3.isChecked){
                ing3 = "Con $ing3recibido"
            }else{
                ing3 = "Sin $ing3recibido"
            }
            if(cbingrediente4.isChecked){
                ing4 = "Con $ing4recibido"
            }else{
                ing4 = "Sin $ing4recibido"
            }
            if(cbingrediente5.isChecked){
                ing5 = "Con $ing5recibido"
            }else{
                ing5 = "Sin $ing5recibido"
            }

            Toast.makeText(this,"La hamburguesa va  $ing1, $ing2, $ing3, $ing4, $ing5",Toast.LENGTH_SHORT).show()
        }


    }
}
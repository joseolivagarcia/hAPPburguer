package joseoliva.com.happburguer

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.paypal.android.sdk.payments.*
import joseoliva.com.happburguer.databinding.ActivityPagarBinding
import org.json.JSONException
import java.math.BigDecimal

class PagarActivity : AppCompatActivity() {

    lateinit var binding: ActivityPagarBinding
    var precioAPagar: Float = 0f

    val clientKey =
        "AYMLUlAaOoyNeKNGkvMv2u7pGuWaDVPGFNPXwIb5F_3TGBu8DEFDzfyAoWtHQWWqpImXrkEtM-a6_6t8"
    val PAYPAL_REQUEST_CODE = 123 //necesitaremos un codigo de respuesta (puede ser cualquiera creo)

    //esto es para usar una cuenta sandbox(de test)
    val config = PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
        .clientId(clientKey) //le pasamos el id que tenemos en la clase config, el que creamos en la web de paypal developers
    var cantidad = ""

    //la siguiente var es para guardar la respuesta del startactivityforresult (ya deprecado)
    private val responseLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            val codigo = activityResult.data?.getIntExtra("code", 0)
            if (codigo == PAYPAL_REQUEST_CODE) {
                if (activityResult.resultCode == RESULT_OK) {
                    Toast.makeText(
                        this,
                        "Pago correcto",
                        Toast.LENGTH_LONG
                    ).show()
                } else if (codigo == RESULT_CANCELED) Toast.makeText(
                    this,
                    "Pago Fallido",
                    Toast.LENGTH_LONG
                ).show()
            } else if (codigo == PaymentActivity.RESULT_EXTRAS_INVALID) Toast.makeText(
                this,
                "Pago Invalidado",
                Toast.LENGTH_LONG
            ).show()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inicio mi toolbar y sobreescribo los metodos necesarios (al final)
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        precioAPagar = intent.getFloatExtra("precio",0f)
        binding.tvcargo.text = precioAPagar.toString() + "€"

        //iniciamos el servidio de paypal
        val intent = Intent(this, PayPalService::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config)
        startService(intent)

        //funcionalidad al boton de cargo
        binding.btncargar.setOnClickListener {
            //recojo la cantidad que haya puesto el usuario
            cantidad = precioAPagar.toString()
            //realizamos el pago
            val payPalPayment = PayPalPayment(
                BigDecimal(cantidad.toString()),
                "EUR",
                "Pago a bAPPburguer",
                PayPalPayment.PAYMENT_INTENT_SALE
            )
            //enviamos los datos a la actividad de Paypal (esta actividad se crea sola, es externa a la app)
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
            intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment)
            intent.putExtra("code",PAYPAL_REQUEST_CODE)
            responseLauncher.launch(intent)
        }

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
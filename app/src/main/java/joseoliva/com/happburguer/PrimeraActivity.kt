package joseoliva.com.happburguer

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.muddz.styleabletoast.StyleableToast
import joseoliva.com.happburguer.databinding.ActivityPrimeraBinding

class PrimeraActivity : AppCompatActivity() {

    lateinit var binding: ActivityPrimeraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrimeraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.burguers.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.pizzas.setOnClickListener {
            //implementando la dependencia StyleableToast creamos Toast personalizados
            StyleableToast
                .Builder(this)
                .text("Próximamente")
                .textColor(Color.WHITE)
                .backgroundColor(Color.parseColor("#592503"))
                .stroke(3, Color.parseColor("#FFFFFFFF"))
                .iconStart(R.drawable.ic_calendar)
                .iconEnd(R.drawable.ic_calendar)
                .show()
        }

        binding.pasta.setOnClickListener {
            //implementando la dependencia StyleableToast creamos Toast personalizados
            StyleableToast
                .Builder(this)
                .text("Próximamente")
                .textColor(Color.WHITE)
                .backgroundColor(Color.parseColor("#592503"))
                .stroke(3, Color.parseColor("#FFFFFFFF"))
                .iconStart(R.drawable.ic_calendar)
                .iconEnd(R.drawable.ic_calendar)
                .show()
        }

        binding.ensalada.setOnClickListener {
            //implementando la dependencia StyleableToast creamos Toast personalizados
            StyleableToast
                .Builder(this)
                .text("Próximamente")
                .textColor(Color.WHITE)
                .backgroundColor(Color.parseColor("#592503"))
                .stroke(3, Color.parseColor("#FFFFFFFF"))
                .iconStart(R.drawable.ic_calendar)
                .iconEnd(R.drawable.ic_calendar)
                .show()
        }
    }

    @Override
    override fun onBackPressed() {

    }
}
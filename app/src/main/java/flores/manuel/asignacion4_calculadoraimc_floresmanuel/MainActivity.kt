package flores.manuel.asignacion4_calculadoraimc_floresmanuel

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Manuel Francisco Flores Velazquez - 233301
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val weight: EditText = findViewById(R.id.weight) as EditText
        val heigth: EditText = findViewById(R.id.height) as EditText
        val imc: TextView = findViewById(R.id.imc) as TextView
        val rango: TextView = findViewById(R.id.range) as TextView
        val calcular: Button = findViewById(R.id.btnCalcular) as Button

        calcular.setOnClickListener {
            var metros = 0.0
            var kilos = 0.0

            try{
                metros = heigth.text.toString().toDouble()
                kilos = weight.text.toString().toDouble()
            } catch(e: java.lang.Exception) {
                imc.setText("Los valores no son adecuados")
            }

            var indice: Double = calcularImc(kilos, metros)
            imc.setText("%.2f".format(indice))

            when{
                indice < 18.5 ->{
                    rango.setBackgroundColor(getColor(R.color.colorYellow))
                    rango.setText("Bajo peso")
                }
                indice >= 18.5 && indice < 25 ->{
                    rango.setBackgroundColor(getColor(R.color.colorGreen))
                    rango.setText("Normal")
                }
                indice >= 25 && indice < 30 ->{
                    rango.setBackgroundColor(getColor(R.color.colorYellow))
                    rango.setText("Sobrepeso")
                }
                indice >= 30 && indice <35 ->{
                    rango.setBackgroundColor(getColor(R.color.colorOrange))
                    rango.setText("Obesidad grado 1")
                }
                indice >= 35 && indice < 40 ->{
                    rango.setBackgroundColor(getColor(R.color.colorRed))
                    rango.setText("Obesidad grado 2")
                }
                indice >= 40 ->{
                    rango.setBackgroundColor(getColor(R.color.colorBrown))
                    rango.setText("Obesidad grado 3")
                }
                else ->{
                    rango.setBackgroundColor(getColor(R.color.white))
                    rango.setText("No se pudo calcular el rango")
                }
            }

        }

    }

    fun calcularImc(peso: Double, estatura: Double): Double{
        return peso/(Math.pow(estatura, 2.0))
    }

}

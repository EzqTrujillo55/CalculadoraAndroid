package com.example.calculadorakotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    //Variables globales que se emplean en los distintos métodos
    private var num1: Double = 0.0
    private var num2: Double = 0.0
    private var operacion:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Especificamos los literners para los botones de números enviando como parámetro el dígito
        unoBoton.setOnClickListener{ numeroPresionado("1")}
        dosBoton.setOnClickListener{ numeroPresionado("2")}
        tresBoton.setOnClickListener{ numeroPresionado("3")}
        cuatroBoton.setOnClickListener{ numeroPresionado("4")}
        cincoBoton.setOnClickListener{ numeroPresionado("5")}
        seisBoton.setOnClickListener{ numeroPresionado("6")}
        sieteBoton.setOnClickListener{ numeroPresionado("7")}
        ochoBoton.setOnClickListener{ numeroPresionado("8")}
        nueveBoton.setOnClickListener{ numeroPresionado("9")}
        ceroBoton.setOnClickListener{ numeroPresionado("0")}
        puntoBoton.setOnClickListener { numeroPresionado(".")}
        //Especificamos los listeners para los botones de operaciones enviando como parámetro la constante
        //correspondiente a la operación
        sumaBoton.setOnClickListener{ operacionPresionada(SUMA)}
        restaBoton.setOnClickListener{ operacionPresionada(RESTA)}
        productoBoton.setOnClickListener{ operacionPresionada(PRODUCTO)}
        divisionBoton.setOnClickListener{ operacionPresionada(DIVISION)}
        potenciaBoton.setOnClickListener{ operacionPresionada(POTENCIA)}
        sqrtBoton.setOnClickListener{ operacionPresionada(RAIZ)}
        //El listener para limpiar, se limpian los dos números, se coloca el valor 0 en resultadoView
        //Y  se asigna el valor de NO_OPERACION
        limpiarBoton.setOnClickListener{
            num1 = 0.0
            num2 = 0.0
            resultadoTextView.text = "0"
            operacion = NO_OPERACION
        }

        //El listener del botón igual, valida de que operacion se trata y ejecuta lo correspondiente
        igualBoton.setOnClickListener {
            var resultado = when(operacion){
                SUMA -> num1+num2
                RESTA -> num1-num2
                PRODUCTO -> num1*num2
                DIVISION -> num1/num2
                POTENCIA -> potencia(num1, num2.toInt())
                RAIZ -> sqrt(num1)
                else -> 0
            }

            num1 = resultado as Double
            //Validamos si el resultado finaliza con .0, en caso de que sí se reemplaza por ""
            //ejemplo 12.0 -> 12
            resultadoTextView.text = if("$resultado".endsWith(".0")) { "$resultado".replace(".0","") } else { "%.2f".format(resultado)}
        }
    }

    //Función que se ejecuta al presioanr un número
    private fun numeroPresionado(digito: String){
        //Valida si el contenido es cero
        //En caso de que sea cero, si el digito que se quiere ingresar es un punto, se concatena
        //Si el digito que se quiere ingresar no es un punto, se reempalza el cero por el digito presionado
        //En caso de que no sea cero, se concatena el contenido actual con el digito presionado
        if(resultadoTextView.text == "0"){
            if(digito==".") {
                resultadoTextView.text = "${resultadoTextView.text}$digito"
            }else{
                resultadoTextView.text = digito
            }
        }else{
            resultadoTextView.text = "${resultadoTextView.text}$digito"
        }

        //Si no hay una operacion presionada, se almacena lo digitado en num1
        //Si sí hay una operación, se almacena lo digitado en num2
        if(operacion== NO_OPERACION){
            num1 = resultadoTextView.text.toString().toDouble()
        }else{
            num2 = resultadoTextView.text.toString().toDouble()
        }
    }

    //Función que se ejecuta cuando se presiona un botón de operacion
    //Se asigna al valor de operacion global, la operación presionada
    //Se limpia el text view colocándolo en cero
    private fun operacionPresionada(operacion: Int){
        this.operacion = operacion
        if(this.operacion != 6){
            resultadoTextView.text = "0"
        }
    }

    //Funcion potencia, recibe 2 parámetros el número y a qué potencia se va a elevar
    //Ejecutamos el bucle para multiplicar el número las veces especificadas en el 2do parametro
    private fun potencia( a: Double,  b: Int): Double {
        var result:Double = 1.0
        for(i in 1..b)
            result = result*a
        return result
    }



    //Lista de constantes para las operaciones
    companion object{
        const val SUMA = 1
        const val RESTA = 2
        const val PRODUCTO = 3
        const val DIVISION = 4
        const val POTENCIA = 5
        const val RAIZ = 6
        const val NO_OPERACION = 0
    }
}
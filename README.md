# Calculadora Android con Kotlin

_Aplicación móvil nativa que cuentas con algunas funcionalidades básicas y avanzadas de una calculadora._

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

Mira **Deployment** para conocer como desplegar el proyecto.


### Pre-requisitos 📋

_Android Studio_


### Instalación 🔧

```
1. Ejecutar el siguiente comando: git clone url de este repositorio
2. Abrir el proyecto en Android Studio
3. Ingresar a GradleScripts/build.gradle(Module:CalculadoraKotlin.app) y verificar que al inicio exista el siguiente contenido
    apply plugin: 'com.android.application'
    apply plugin: 'kotlin-android'
    apply plugin: 'kotlin-android-extensions'
4. Click en el icono de ejecución. 
5. Importante haber creado antes dispositivos virtuales para ver la app en ejecución o conectar un dispositivo físico con la depuración USB Activada
```


## Despliegue 📦

```
1. En android studio click en Build, luego en Generate signed bundle / apk
2. Seguir los pasos del asistente
```


## Funcionalidades 📖
Previo a los cálculos es importante indicar la lógica que se maneja para la interacción del Usuario:
1. Cada botón tiene un listener, el cual invoca uan función o tiene su propia lógica embebida, para el caso de los botones de números el código es el siguiente
    ```
     //Dentro de la función onCreate especificamos los literners para los botones de números enviando como parámetro el dígito
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
    
    /Función que se ejecuta al presioanr un número
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
    ```
2. Para la interacción con los botones de operaciones, funciona de manera similar, el código es el siguiente, es importante también definir algunas constantes para las operaciones 
    ```
    //En la función onCreate especificamos los listeners para los botones de operaciones enviando como parámetro la constante
    //correspondiente a la operación
        sumaBoton.setOnClickListener{ operacionPresionada(SUMA)}
        restaBoton.setOnClickListener{ operacionPresionada(RESTA)}
        productoBoton.setOnClickListener{ operacionPresionada(PRODUCTO)}
        divisionBoton.setOnClickListener{ operacionPresionada(DIVISION)}
        potenciaBoton.setOnClickListener{ operacionPresionada(POTENCIA)}
        sqrtBoton.setOnClickListener{ operacionPresionada(RAIZ)}
        //Función que se ejecuta cuando se presiona un botón de operacion
        //Se asigna al valor de operacion global, la operación presionada
        //Se limpia el text view colocándolo en cero
        private fun operacionPresionada(operacion: Int){
            this.operacion = operacion
            if(this.operacion != 6){
                resultadoTextView.text = "0"
            }
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
    ```
3. Para el botón C, simplemente realizamos algunas asignaciones para "reiniciar" la App. 
    ```
        //El listener para limpiar, se enceran los dos números, se coloca el valor 0 en resultadoView
        //Y  se asigna el valor de NO_OPERACION
        limpiarBoton.setOnClickListener{
            num1 = 0.0
            num2 = 0.0
            resultadoTextView.text = "0"
            operacion = NO_OPERACION
        }
    ```
    
4. El botón de igual es el que se encarga de validar las operaciones y ejecutar lo correspondiente 
    1. Suma: num1 + num2
    2. Resta: num1 - num2
    3. Producto: num1 * num2
    4. Divisón: num1 / num2
    5. Potenciación
        - Para la potenciación se ejecuta una función que recibe 2 parámetros, uno de tipo double (base) y un tipo entero (exponente) 
        - El exponente indica las veces que se ejecutará base * base 
        - Esto lo logramos mediante un bucle for
        - Código 
        ```
           private fun potencia( a: Double,  b: Int): Double {
            var result:Double = 1.0
            for(i in 1..b)
                result = result*a
            return result
            }
        ```
6. Radicación
    - Para la radicación en lugar de emplear una función aparte, nos valemos de la función nativa de Kotlin, sqrt
    - Código 
    ```
    RAIZ -> sqrt(num1)
    ```
    

## Preview 


## Construido con 🛠️

_Menciona las herramientas que utilizaste para crear tu proyecto_

* [Android Studio](https://developer.android.com/studio) - Entorno de desarrollo para aplicaciones nativas
* [Kotlin](https://kotlinlang.org) - Lenguaje de programación oficial de Android para desarrollo de Apps Móviles


## Autores ✒️

_Menciona a todos aquellos que ayudaron a levantar el proyecto desde sus inicios_

* **Gabriela García** - *Codificación, Investigación, Documentación* - [villanuevand](https://github.com/Gabiita)
* **Pablo Trujillo** - *Codificación, Investigación, Documentación* - [fulanitodetal](https://github.com/EzqTrujillo55)


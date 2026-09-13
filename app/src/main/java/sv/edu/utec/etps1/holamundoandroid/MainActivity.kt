package sv.edu.utec.etps1.holamundoandroid

import android.content.Intent
import android.os.Bundle

// ==========================================================
// ACTIVITY
// ==========================================================

import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent


// ==========================================================
// IMPORTS PARA LA MAQUETACIÓN
// ==========================================================

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width


// ==========================================================
// COMPONENTES VISUALES
// ==========================================================

import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface


// ==========================================================
// ICONOS
// ==========================================================

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person


// ==========================================================
// COMPOSE
// ==========================================================

import androidx.compose.runtime.Composable

// IMPORTANTE:
// Permite obtener el contexto actual de Android
// desde una función @Composable.

import androidx.compose.ui.platform.LocalContext


// ==========================================================
// UI
// ==========================================================

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// ==========================================================
// TEMA
// ==========================================================

import sv.edu.utec.etps1.holamundoandroid.ui.theme.HolaMundoAndroidTheme



// ==========================================================
// MAIN ACTIVITY
// ==========================================================

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        // ==================================================
        // CONTENIDO DE LA PANTALLA
        // ==================================================

        setContent {

            HolaMundoAndroidTheme {

                Surface(

                    modifier = Modifier.fillMaxSize(),

                    color = Color(0xFF5A1533)

                ) {

                    PantallaPrincipal()

                }
            }
        }
    }
}



// ==========================================================
// PANTALLA PRINCIPAL
// ==========================================================

@Composable
fun PantallaPrincipal() {

    // ======================================================
    // CONTEXTO DE ANDROID
    // ======================================================

    // Como esta función es @Composable,
    // no podemos utilizar directamente:
    //
    // this@MainActivity
    //
    // ni:
    //
    // startActivity()
    //
    // Por eso obtenemos el contexto actual de Android.

    val context = LocalContext.current


    // ======================================================
    // CONTENEDOR PRINCIPAL
    // ======================================================

    Column(

        modifier = Modifier.fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {


        // ==================================================
        // MATERIA
        // ==================================================

        Row(

            verticalAlignment = Alignment.CenterVertically

        ) {

            // ------------------------------------------------
            // ICONO MATERIA
            // ------------------------------------------------

            Icon(

                imageVector = Icons.Default.MenuBook,

                contentDescription = "Materia",

                tint = Color(0xFFFFD54F)

            )


            // ------------------------------------------------
            // ESPACIO
            // ------------------------------------------------

            Spacer(
                modifier = Modifier.width(8.dp)
            )


            // ------------------------------------------------
            // TEXTO
            // ------------------------------------------------

            Text(

                text = "Materia: ETPS1",

                color = Color.White,

                fontSize = 20.sp

            )
        }



        // ==================================================
        // ESPACIO
        // ==================================================

        Spacer(
            modifier = Modifier.height(16.dp)
        )



        // ==================================================
        // ALUMNO
        // ==================================================

        Row(

            verticalAlignment = Alignment.CenterVertically

        ) {

            // ------------------------------------------------
            // ICONO ALUMNO
            // ------------------------------------------------

            Icon(

                imageVector = Icons.Default.Person,

                contentDescription = "Alumno",

                tint = Color(0xFF81D4FA)

            )


            // ------------------------------------------------
            // ESPACIO
            // ------------------------------------------------

            Spacer(
                modifier = Modifier.width(8.dp)
            )


            // ------------------------------------------------
            // TEXTO
            // ------------------------------------------------

            Text(

                text = "Alumno: Herberth Segovia",

                color = Color.White,

                fontSize = 20.sp

            )
        }



        // ==================================================
        // ESPACIO
        // ==================================================

        Spacer(
            modifier = Modifier.height(16.dp)
        )



        // ==================================================
        // CARNET
        // ==================================================

        Row(

            verticalAlignment = Alignment.CenterVertically

        ) {

            // ------------------------------------------------
            // ICONO CARNET
            // ------------------------------------------------

            Icon(

                imageVector = Icons.Default.Badge,

                contentDescription = "Carnet",

                tint = Color(0xFFA5D6A7)

            )


            // ------------------------------------------------
            // ESPACIO
            // ------------------------------------------------

            Spacer(
                modifier = Modifier.width(8.dp)
            )


            // ------------------------------------------------
            // TEXTO
            // ------------------------------------------------

            Text(

                text = "Carnet: 2930342021",

                color = Color.White,

                fontSize = 20.sp

            )
        }



        // ==================================================
        // ESPACIO ANTES DEL BOTÓN
        // ==================================================

        Spacer(
            modifier = Modifier.height(32.dp)
        )



        // ==================================================
        // BOTÓN REGISTRO DE INCIDENCIAS
        // ==================================================

        Button(

            onClick = {

                // ==================================================
                // CREAR INTENT
                // ==================================================

                // Ahora utilizamos el contexto que obtuvimos
                // mediante:
                //
                // val context = LocalContext.current

                val intent = Intent(

                    context,

                    RegistroIncidenciasActivity::class.java

                )


                // ==================================================
                // ABRIR REGISTRO DE INCIDENCIAS
                // ==================================================

                context.startActivity(intent)

            }

        ) {


            // ==================================================
            // ICONO DEL BOTÓN
            // ==================================================

            Icon(

                imageVector = Icons.Default.EditNote,

                contentDescription = "Registro de incidencias",

                tint = Color.White

            )


            // ==================================================
            // ESPACIO
            // ==================================================

            Spacer(
                modifier = Modifier.width(8.dp)
            )


            // ==================================================
            // TEXTO DEL BOTÓN
            // ==================================================

            Text(

                text = "Registro incidencias"

            )
        }
    }
}
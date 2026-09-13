package sv.edu.utec.etps1.holamundoandroid

import android.os.Bundle

// ==========================================================
// ACTIVITY
// ==========================================================

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


// ==========================================================
// MAQUETACIÓN
// ==========================================================

// Column equivale conceptualmente a:
//
// <LinearLayout
//     android:orientation="vertical">

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding


// ==========================================================
// COMPONENTES VISUALES
// ==========================================================

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text


// ==========================================================
// COMPOSE
// ==========================================================

import androidx.compose.runtime.Composable


// ==========================================================
// UI
// ==========================================================

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


// ==========================================================
// TEMA
// ==========================================================

import sv.edu.utec.etps1.holamundoandroid.ui.theme.HolaMundoAndroidTheme



// ==========================================================
// REGISTRO DE INCIDENCIAS ACTIVITY
// ==========================================================

class RegistroIncidenciasActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        // ==================================================
        // CONTENIDO DE LA PANTALLA
        // ==================================================

        setContent {

            HolaMundoAndroidTheme {

                RegistroIncidenciasApp()

            }
        }
    }
}



// ==========================================================
// PANTALLA REGISTRO DE INCIDENCIAS
// ==========================================================

@Composable
fun RegistroIncidenciasApp() {

    // ======================================================
    // CONTENEDOR PRINCIPAL
    // ======================================================

    // En XML podemos imaginar:
    //
    // <LinearLayout
    //     android:layout_width="match_parent"
    //     android:layout_height="match_parent"
    //     android:orientation="vertical"
    //     android:padding="24dp"
    //     android:gravity="center">

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        // Centra verticalmente el contenido.

        verticalArrangement = Arrangement.Center,

        // Centra horizontalmente los elementos.

        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        // ==================================================
        // TÍTULO
        // ==================================================

        // Equivalente conceptual a:
        //
        // <TextView
        //     android:text="Registro de incidencias" />

        Text(

            text = "Registro de incidencias",

            style = MaterialTheme.typography.headlineMedium

        )


        // ==================================================
        // ESPACIO
        // ==================================================

        // Equivalente conceptual a un margen vertical.

        Spacer(

            modifier = Modifier.height(12.dp)

        )


        // ==================================================
        // DESCRIPCIÓN
        // ==================================================

        Text(

            text = "Reporta y da seguimiento a problemas de equipos, " +
                    "infraestructura o servicios."

        )


        // ==================================================
        // ESPACIO
        // ==================================================

        Spacer(

            modifier = Modifier.height(24.dp)

        )


        // ==================================================
        // CARD
        // ==================================================

        // Card es una tarjeta visual.
        //
        // Conceptualmente podemos pensar:
        //
        // <CardView>

        Card(

            modifier = Modifier.fillMaxWidth()

        ) {

            // ------------------------------------------------
            // CONTENIDO DE LA TARJETA
            // ------------------------------------------------

            Column(

                modifier = Modifier.padding(16.dp)

            ) {


                // ==========================================
                // TÍTULO DE LA TARJETA
                // ==========================================

                Text(

                    text = "Estado inicial",

                    style = MaterialTheme.typography.titleMedium

                )


                // ==========================================
                // MENSAJE
                // ==========================================

                Text(

                    text = "Aún no hay incidencias registradas."

                )
            }
        }


        // ==================================================
        // ESPACIO
        // ==================================================

        Spacer(

            modifier = Modifier.height(24.dp)

        )


        // ==================================================
        // BOTÓN CREAR REPORTE
        // ==================================================

        Button(

            onClick = {

                // Por ahora no hacemos nada.

                // En una etapa posterior aquí
                // implementaremos el registro
                // de una nueva incidencia.

            }

        ) {

            Text(

                text = "Crear reporte"

            )
        }
    }
}



// ==========================================================
// PREVIEW
// ==========================================================

// Preview nos permite visualizar la pantalla
// directamente dentro de Android Studio
// sin ejecutar toda la aplicación.

@Preview(

    showBackground = true

)

@Composable
fun RegistroIncidenciasPreview() {

    HolaMundoAndroidTheme {

        RegistroIncidenciasApp()

    }
}
/*
* ============================================================================
* CONTROL DE VERSIONES
* ============================================================================
* Estado           : MODIFICACIÓN
* Archivo          : MainActivity.kt
* Ruta             : app/src/main/java/sv/edu/utec/etps1/holamundoandroid/MainActivity.kt
* Versión anterior : V0.3
* Nueva versión    : V0.4
* Fecha            : 2026-11-02
* Propósito Técnico: Instrumentación de callbacks del ciclo de vida de la Activity
*                    con Log.d bajo la etiqueta TPIS1_LIFECYCLE para la bitácora
*                    evaluada de la Semana 14.
* ============================================================================
*/
package sv.edu.utec.etps1.holamundoandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sv.edu.utec.etps1.holamundoandroid.ui.theme.HolaMundoAndroidTheme

class MainActivity : ComponentActivity() {

    private val tag = "TPIS1_LIFECYCLE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate: Activity creada e inicializando UI")

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

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart: Activity visible para el usuario")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume: Activity en primer plano y lista para interactuar")
    }

    override fun onPause() {
        Log.d(tag, "onPause: Activity perdiendo foco o pasando a segundo plano")
        super.onPause()
    }

    override fun onStop() {
        Log.d(tag, "onStop: Activity oculta, en segundo plano")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(tag, "onDestroy: Activity destruida, liberando memoria del sistema")
        super.onDestroy()
    }
}

@Composable
fun PantallaPrincipal() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // MATERIA
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.MenuBook,
                contentDescription = "Materia",
                tint = Color(0xFFFFD54F)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Materia: ETPS1",
                color = Color.White,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ALUMNO
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Alumno",
                tint = Color(0xFF81D4FA)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Alumno: Herberth Segovia",
                color = Color.White,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // CARNET
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Badge,
                contentDescription = "Carnet",
                tint = Color(0xFFA5D6A7)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Carnet: 2930342021",
                color = Color.White,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // BOTÓN NAVEGACIÓN
        Button(
            onClick = {
                val intent = Intent(
                    context,
                    RegistroIncidenciasActivity::class.java
                )
                context.startActivity(intent)
            }
        ) {
            Icon(
                imageVector = Icons.Default.EditNote,
                contentDescription = "Registro de incidencias",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Registro incidencias")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // VERSIÓN ACTUALIZADA
        Text(
            text = "VRSN 0.4 02112026 1640",
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPrincipalPreview() {
    HolaMundoAndroidTheme {
        Surface(color = Color(0xFF5A1533)) {
            PantallaPrincipal()
        }
    }
}
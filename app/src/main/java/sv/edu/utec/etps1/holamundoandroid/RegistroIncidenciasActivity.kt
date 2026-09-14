package sv.edu.utec.etps1.holamundoandroid

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sv.edu.utec.etps1.holamundoandroid.ui.theme.HolaMundoAndroidTheme

class RegistroIncidenciasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HolaMundoAndroidTheme {
                RegistroIncidenciasApp()
            }
        }
    }
}

@Composable
fun RegistroIncidenciasApp() {
    val context = LocalContext.current
    val activity = context as? Activity

    // ======================================================
    // VARIABLES SIMULADAS DE USUARIO
    // ======================================================
    val usuarioSimulado = "Antonio Orellana"
    val departamentoSimulado = "Contabilidad"

    // ======================================================
    // LÍMITES DE CARACTERES
    // ======================================================
    val maxTitulo = 50
    val maxDescripcion = 200

    // ======================================================
    // ESTADOS LOCALES DE CAMPOS Y DIÁLOGO
    // ======================================================
    var tituloProblem by remember { mutableStateOf("") }
    var descripcionProblem by remember { mutableStateOf("") }
    var mostrarDialogo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // ==================================================
        // SALUDO PERSONALIZADO
        // ==================================================
        Text(
            text = "¡Hola, $usuarioSimulado! 👋",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5A1533)
        )

        Text(
            text = "Área: $departamentoSimulado | Soporte IT",
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            color = Color(0xFF1976D2)
        )

        Text(
            text = "Gracias por comunicarte con IT, ¿en qué podemos ayudarte?",
            fontSize = 13.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )

        // ==================================================
        // CAMPO 1: TÍTULO CON VALIDACIÓN DE CARACTERES
        // ==================================================
        OutlinedTextField(
            value = tituloProblem,
            onValueChange = { if (it.length <= maxTitulo) tituloProblem = it },
            label = { Text("¿Qué problemas estás presentando?") },
            placeholder = { Text("Ej: Cálculo de retenciones de liquidación") },
            supportingText = {
                Text(
                    text = "${tituloProblem.length} / $maxTitulo car.",
                    modifier = Modifier.fillMaxWidth(),
                    color = if (tituloProblem.length == maxTitulo) Color.Red else Color.Gray
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ==================================================
        // CAMPO 2: DESCRIPCIÓN CON VALIDACIÓN DE CARACTERES
        // ==================================================
        OutlinedTextField(
            value = descripcionProblem,
            onValueChange = { if (it.length <= maxDescripcion) descripcionProblem = it },
            label = { Text("Descríbenos el problema") },
            placeholder = { Text("Ej: Al momento de realizar la liquidación el cálculo de retenciones no es correcto") },
            supportingText = {
                Text(
                    text = "${descripcionProblem.length} / $maxDescripcion car.",
                    modifier = Modifier.fillMaxWidth(),
                    color = if (descripcionProblem.length == maxDescripcion) Color.Red else Color.Gray
                )
            },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            maxLines = 4
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ==================================================
        // BOTONES DE ACCIÓN (CANCELAR Y REPORTAR)
        // ==================================================
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Botón Cancelar (Rojo / Cierra actividad y regresa)
            OutlinedButton(
                onClick = {
                    activity?.finish()
                },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFC62828)),
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Cancel,
                    contentDescription = "Cancelar",
                    tint = Color(0xFFC62828)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Cancelar")
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Botón Reportar (Verde / Muestra PopUp)
            Button(
                onClick = {
                    if (tituloProblem.isNotBlank() && descripcionProblem.isNotBlank()) {
                        mostrarDialogo = true
                    } else {
                        Toast.makeText(context, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Reportar",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Reportar")
            }
        }
    }

    // ======================================================
    // POPUP DE CONFIRMACIÓN (AlertDialog)
    // ======================================================
    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = { /* No permite cerrar tocando fuera */ },
            icon = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Éxito",
                    tint = Color(0xFF2E7D32),
                    modifier = Modifier.height(40.dp).width(40.dp)
                )
            },
            title = {
                Text(
                    text = "Incidencia Registrada",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5A1533)
                )
            },
            text = {
                Text(
                    text = "Tu número de incidencia es INCD-001.\n\nPronto nos comunicaremos para darle seguimiento a tu solicitud.",
                    fontSize = 14.sp
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarDialogo = false
                        activity?.finish() // Finaliza la actividad actual para regresar a MainActivity
                    }
                ) {
                    Text(
                        text = "Aceptar",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroIncidenciasPreview() {
    HolaMundoAndroidTheme {
        RegistroIncidenciasApp()
    }
}
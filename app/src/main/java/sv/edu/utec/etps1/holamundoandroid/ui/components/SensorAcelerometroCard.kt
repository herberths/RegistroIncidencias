/*
 * ============================================================================
 * CONTROL DE VERSIONES
 * ============================================================================
 * Estado           : NUEVO
 * Archivo          : SensorAcelerometroCard.kt
 * Ruta             : app/src/main/java/sv/edu/utec/etps1/holamundoandroid/ui/components/SensorAcelerometroCard.kt
 * Versión          : V0.1
 * Fecha            : 2026-10-11
 * Propósito Técnico: Componente reutilizable en Jetpack Compose para la lectura
 *                    y visualización en tiempo real de las coordenadas triaxiales
 *                    (X, Y, Z) del sensor de acelerómetro (Sensor.TYPE_ACCELEROMETER)
 *                    gestionando de forma segura el ciclo de vida con DisposableEffect.
 * ============================================================================
 */

package sv.edu.utec.etps1.holamundoandroid.ui.components

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@Composable
fun SensorAcelerometroCard(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Obtención del SensorManager desde el Contexto del sistema
    val sensorManager = remember {
        context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
    }

    // Referencia al sensor físico de acelerómetro
    val acelerometro = remember(sensorManager) {
        sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    // Estados reactivos para almacenar las coordenadas triaxiales
    var ejeX by remember { mutableFloatStateOf(0f) }
    var ejeY by remember { mutableFloatStateOf(0f) }
    var ejeZ by remember { mutableFloatStateOf(0f) }
    var sensorDisponible by remember { mutableStateOf(acelerometro != null) }

    // ========================================================================
    // GESTIÓN DEL CICLO DE VIDA DEL SENSOR CON DISPOSABLEEFFECT
    // ========================================================================
    DisposableEffect(sensorManager, acelerometro) {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                    ejeX = event.values[0]
                    ejeY = event.values[1]
                    ejeZ = event.values[2]
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // Sin acción requerida para cambio de precisión
            }
        }

        if (acelerometro != null) {
            sensorDisponible = true
            sensorManager?.registerListener(
                listener,
                acelerometro,
                SensorManager.SENSOR_DELAY_UI
            )
        } else {
            sensorDisponible = false
        }

        // Limpieza segura del listener al destruir o desvincular el Composable
        onDispose {
            sensorManager?.unregisterListener(listener)
        }
    }

    // ========================================================================
    // TARJETA VISUAL DEL ACELERÓMETRO (MATERIAL 3)
    // ========================================================================
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF3E5F5) // Tono púrpura suave adaptado al tema
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // ENCABEZADO DE LA TARJETA
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Sensors,
                    contentDescription = "Sensor Acelerómetro",
                    tint = Color(0xFF5A1533)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Acelerómetro en Tiempo Real",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5A1533)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (sensorDisponible) {
                // LECTURA DE COORDENADAS TRIAXIALES
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CoordenadaItem(label = "Eje X", valor = ejeX, colorBg = Color(0xFFFFEBEE), colorText = Color(0xFFC62828))
                    CoordenadaItem(label = "Eje Y", valor = ejeY, colorBg = Color(0xFFE8F5E9), colorText = Color(0xFF2E7D32))
                    CoordenadaItem(label = "Eje Z", valor = ejeZ, colorBg = Color(0xFFE3F2FD), colorText = Color(0xFF1565C0))
                }
            } else {
                Text(
                    text = "⚠️ El sensor de acelerómetro no está disponible en este dispositivo.",
                    color = Color(0xFFC62828),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun CoordenadaItem(
    label: String,
    valor: Float,
    colorBg: Color,
    colorText: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = colorBg, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = colorText
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = String.format(Locale.US, "%.2f", valor),
            fontSize = 14.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
        Text(
            text = "m/s²",
            fontSize = 9.sp,
            color = Color.Gray
        )
    }
}

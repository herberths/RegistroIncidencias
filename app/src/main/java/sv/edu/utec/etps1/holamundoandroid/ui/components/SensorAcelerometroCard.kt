/*
- ============================================================================
- CONTROL DE VERSIONES
- ============================================================================
- Estado           : ACTUALIZACIÓN / INTEGRACIÓN SEMANA 12
- Archivo          : SensorAcelerometroCard.kt
- Ruta             : app/src/main/java/sv/edu/utec/etps1/holamundoandroid/ui/components/SensorAcelerometroCard.kt
- Versión          : V0.2
- Fecha            : 2026-10-18
- Propósito Técnico: Componente modular de diagnóstico del hardware (Acelerómetro).
-                    Comprueba disponibilidad, escucha eventos de movimiento con
-                    SensorEventListener, controla el ciclo de vida mediante
-                    DisposableEffect y presenta telemetría con interpretación visible.
- ============================================================================
 */

package sv.edu.utec.etps1.holamundoandroid.ui.components

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.SensorsOff
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import kotlin.math.sqrt

@Composable
fun SensorAcelerometroCard(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Estados reactivos para Compose
    var sensorDisponible by remember { mutableStateOf(true) }
    var mensajeInterpretativo by remember { mutableStateOf("Esperando lectura del sensor...") }
    var colorInterpretacion by remember { mutableStateOf(Color(0xFF1976D2)) }

    var valorX by remember { mutableFloatStateOf(0f) }
    var valorY by remember { mutableFloatStateOf(0f) }
    var valorZ by remember { mutableFloatStateOf(0f) }

    // Control del ciclo de vida y escucha con DisposableEffect
    DisposableEffect(Unit) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
        val acelerometro = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (sensorManager == null || acelerometro == null) {
            sensorDisponible = false
            mensajeInterpretativo = "Acelerómetro no disponible en este hardware/emulador."
            colorInterpretacion = Color(0xFFC62828)
            onDispose { }
        } else {
            val listener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent) {
                    val x = event.values[0]
                    val y = event.values[1]
                    val z = event.values[2]

                    valorX = x
                    valorY = y
                    valorZ = z

                    // Magnitud vectorial: sqrt(x^2 + y^2 + z^2)
                    // Gravedad estándar en reposo ~ 9.81 m/s^2
                    val aceleracionTotal = sqrt((x * x + y * y + z * z).toDouble())

                    // Interpretación textual contextual según la intensidad
                    when {
                        aceleracionTotal > 14.5 -> {
                            mensajeInterpretativo = "¡Movimiento brusco / sacudida detectada!"
                            colorInterpretacion = Color(0xFFC62828) // Rojo
                        }
                        aceleracionTotal < 6.5 -> {
                            mensajeInterpretativo = "Dispositivo en movimiento descendente / caída libre"
                            colorInterpretacion = Color(0xFFEF6C00) // Naranja
                        }
                        else -> {
                            mensajeInterpretativo = "Dispositivo estable / en reposo"
                            colorInterpretacion = Color(0xFF2E7D32) // Verde
                        }
                    }
                }

                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
            }

            // Registrar con frecuencia UI
            sensorManager.registerListener(
                listener,
                acelerometro,
                SensorManager.SENSOR_DELAY_UI
            )

            // Liberación obligatoria para evitar fugas de memoria y drenaje de batería
            onDispose {
                sensorManager.unregisterListener(listener)
            }
        }
    }

    // Tarjeta visual del Tablero de Diagnóstico
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF7F4F6)
        ),
        border = BorderStroke(1.dp, Color(0xFFE0D3D8))
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Encabezado del módulo
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (sensorDisponible) Icons.Default.Sensors else Icons.Default.SensorsOff,
                    contentDescription = "Diagnóstico Sensor",
                    tint = if (sensorDisponible) Color(0xFF5A1533) else Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Diagnóstico del dispositivo",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF5A1533)
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp),
                thickness = 0.8.dp,
                color = Color(0xFFE0D3D8)
            )

            if (!sensorDisponible) {
                Text(
                    text = mensajeInterpretativo,
                    color = colorInterpretacion,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            } else {
                // Lectura numérica de coordenadas
                Text(
                    text = String.format("Acelerómetro: X: %.2f | Y: %.2f | Z: %.2f m/s²", valorX, valorY, valorZ),
                    fontSize = 11.sp,
                    color = Color.DarkGray
                )

                // Interpretación en lenguaje natural
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Vibration,
                        contentDescription = "Estado dinámico",
                        tint = colorInterpretacion,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Estado: $mensajeInterpretativo",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorInterpretacion
                    )
                }
            }
        }
    }
}
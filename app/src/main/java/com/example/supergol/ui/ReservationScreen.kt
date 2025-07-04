package com.example.supergol.ui

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.supergol.R
import com.example.supergol.viewmodel.SupergolViewModel
import java.util.*
import com.example.supergol.data.model.Reserva
import androidx.compose.material3.*


import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationScreen(navController: NavController, viewModel: SupergolViewModel) {
    val context = LocalContext.current

    val sedes = listOf("Tunal", "Américas", "Kennedy")
    val tipos = listOf("Fútbol 5", "Fútbol 8", "Fútbol 11")

    var sede by remember { mutableStateOf("") }
    var sedeExpanded by remember { mutableStateOf(false) }

    var tipoSeleccionado by remember { mutableStateOf("") }
    var tipoExpanded by remember { mutableStateOf(false) }

    var fecha by remember { mutableStateOf("") }
    var horaSeleccionada by remember { mutableStateOf<String?>(null) }

    val horasDisponibles = (7..23).map { "$it:00" }

    val calendar = Calendar.getInstance()
    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, day ->
            fecha = "$day/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título con logo
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "SUPERGOL",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Divider(color = Color.Gray, thickness = 1.dp)

        // ComboBox Sede
        ExposedDropdownMenuBox(
            expanded = sedeExpanded,
            onExpandedChange = { sedeExpanded = !sedeExpanded }
        ) {
            OutlinedTextField(
                value = sede,
                onValueChange = {},
                readOnly = true,
                label = { Text("Selecciona la sede") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = sedeExpanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = sedeExpanded,
                onDismissRequest = { sedeExpanded = false }
            ) {
                sedes.forEach {
                    DropdownMenuItem(onClick = {
                        sede = it
                        sedeExpanded = false
                    }) {
                        Text(it)
                    }
                }
            }
        }

        // ComboBox Tipo de cancha
        ExposedDropdownMenuBox(
            expanded = tipoExpanded,
            onExpandedChange = { tipoExpanded = !tipoExpanded }
        ) {
            OutlinedTextField(
                value = tipoSeleccionado,
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipo de cancha") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = tipoExpanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = tipoExpanded,
                onDismissRequest = { tipoExpanded = false }
            ) {
                tipos.forEach {
                    DropdownMenuItem(onClick = {
                        tipoSeleccionado = it
                        tipoExpanded = false
                    }) {
                        Text(it)
                    }
                }
            }
        }

        // Fecha
        Button(
            onClick = { datePicker.show() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (fecha.isEmpty()) "Seleccionar fecha" else "Fecha: $fecha")
        }

        // Horas disponibles
        Text("Selecciona una hora:", color = Color.White, fontSize = 16.sp)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(horasDisponibles) { hora ->
                ChipItem(hora, hora == horaSeleccionada) { horaSeleccionada = hora }
            }
        }

        // Imagen cancha
        // Imagen dinámica según sede
        Text("Vista de la cancha:", color = Color.White, fontSize = 16.sp)

        val imagenSede = when (sede) {
            "Tunal" -> R.drawable.tunal
            "Américas" -> R.drawable.americas
            "Kennedy" -> R.drawable.kennedy
            else -> R.drawable.sample_cancha // imagen por defecto
        }

        Image(
            painter = painterResource(id = imagenSede),
            contentDescription = "Imagen cancha",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(vertical = 8.dp)
        )


        // QR Nequi
        Text("Pago del 50% con Nequi:", color = Color.White, fontSize = 16.sp)
        Image(
            painter = painterResource(id = R.drawable.qr_nequi),
            contentDescription = "QR Nequi",
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(vertical = 8.dp)
        )

        // Botón Confirmar
        Button(
            onClick = {
                if (sede.isNotEmpty() && tipoSeleccionado.isNotEmpty() && fecha.isNotEmpty() && horaSeleccionada != null) {
                    viewModel.crearReserva(
                        Reserva(
                            sede = sede,
                            fecha = fecha,
                            hora = horaSeleccionada!!,
                            tipo = tipoSeleccionado
                        )
                    )
                    navController.navigate("home")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirmar Reserva")
        }
    }
}


@Composable
fun ChipItem(label: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = if (selected) Color.Green else Color.DarkGray,
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text = label,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}




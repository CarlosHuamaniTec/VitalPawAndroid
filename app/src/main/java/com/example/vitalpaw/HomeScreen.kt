package com.example.vitalpaw


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.ui.draw.clip
import androidx.compose.material3.Card
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Mascota") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // 1. Tarjeta con información de la mascota
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.perfil_user),
                    contentDescription = "Foto de la mascota",
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text("Max", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text("Labrador · 3 años", fontSize = 14.sp)
                }
            }

            // 2. Estado general
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFDFF0D8))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Estado general", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("✅ Tu mascota está saludable")
                }
            }

            // 3. Signos vitales
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Signos Vitales", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(Modifier.height(8.dp))
                    Text("❤️ Frecuencia cardíaca: 110 bpm")
                    Text("🌡️ Temperatura: 38.5 °C")
                    Text("💨 Movimiento: 25 ")
                }
            }

            // 4. Botones de acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { /* Navegar a historial */ }) {
                    Icon(Icons.Default.ShowChart, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Historial")
                }

                Button(onClick = { /* Contactar veterinario */ }) {
                    Icon(Icons.Default.LocalHospital, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Veterinario")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 5. Botón de emergencia
            Button(
                onClick = { /* Acción de emergencia */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Warning, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("¡Emergencia!", fontWeight = FontWeight.Bold)
            }
        }
    }
}
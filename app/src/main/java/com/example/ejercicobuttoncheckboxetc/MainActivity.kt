package com.example.ejercicobuttoncheckboxetc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejercicobuttoncheckboxetc.ui.theme.EjercicobuttoncheckboxetcTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjercicobuttoncheckboxetcTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        MainScreen(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()


    var isChecked by remember { mutableStateOf(false) }

    var dynamicMessage by remember { mutableStateOf("este es el mensaje activado") }

    var isSwitchOn by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("mensaje 1") }


    var currentImageRes by remember { mutableStateOf(R.drawable.moto1) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = currentImageRes),
            contentDescription = "Imagen de la moto",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 16.dp)
        )


        Button(
            onClick = {
                isLoading = true


                currentImageRes = when (currentImageRes) {
                    R.drawable.moto1 -> R.drawable.moto2
                    R.drawable.moto2 -> R.drawable.moto3
                    else -> R.drawable.moto1
                }

                coroutineScope.launch {
                    delay(5000L) // 5 segundos
                    isLoading = false
                }
            },
            enabled = !isLoading
        ) {
            Text(text = if (isLoading) "Cargando..." else "Presionar")
        }

        Spacer(modifier = Modifier.height(16.dp))


        if (isLoading) {
            CircularProgressIndicator()
        }

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Activar")
        }

        if (isChecked) {
            Text(
                text = dynamicMessage,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Mostrar opciones")
            Spacer(modifier = Modifier.width(16.dp))
            Switch(
                checked = isSwitchOn,
                onCheckedChange = { isSwitchOn = it }
            )
        }

        if (isSwitchOn) {
            Spacer(modifier = Modifier.height(8.dp))
            Column(horizontalAlignment = Alignment.Start) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedOption == "mensaje 1",
                        onClick = {
                            selectedOption = "mensaje 1"
                            dynamicMessage = "mensaje 1"
                        }
                    )
                    Text(text = "Opción 1")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedOption == "mensaje 2",
                        onClick = {
                            selectedOption = "mensaje 2"
                            dynamicMessage = "mensaje 2"
                        }
                    )
                    Text(text = "Opción 2")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedOption == "mensaje 3",
                        onClick = {
                            selectedOption = "mensaje 3"
                            dynamicMessage = "mensaje 3"
                        }
                    )
                    Text(text = "Opción 3")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    EjercicobuttoncheckboxetcTheme {
        MainScreen()
    }
}
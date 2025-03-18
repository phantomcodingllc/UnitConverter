package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var userValue by remember { mutableStateOf("")}
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters")}
    var iExpanded by remember { mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false)}
    val conversionFactor = remember { mutableStateOf(1.00)}
    val conversionOutputFactor = remember { mutableStateOf(1.00)}


    fun convertUnits(){
        // ?: - either returns the result of a double or null (or 0.0)
        val userValueDouble = userValue.toDoubleOrNull() ?: 0.0
        val result = (userValueDouble * conversionFactor.value * 100.0 / conversionOutputFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }


    Column (Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        // Elements will be stacked below each other
        
        Text("Unit Converter",
            style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = userValue,
            onValueChange ={
            // This is what should happen
            // when the value of OutlinedTextField changes
           userValue = it
           convertUnits()
        },
            label = {Text("Enter Value",
                fontWeight = FontWeight.Bold
            )}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //Input box
        Box{
            //Input button
            Button(onClick = { iExpanded = true }) {
                Text(text = inputUnit)
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
            }
            //Dropdown menu for left side, has menu item for each individual conversion
            DropdownMenu(expanded = iExpanded,
                onDismissRequest = { iExpanded = false  }) {
                DropdownMenuItem(text = { Text("Millimeters") },
                    onClick = {
                        iExpanded = false
                        inputUnit = "Millimeters"
                        conversionFactor.value = 0.001
                        convertUnits()
                    })

                DropdownMenuItem(text = { Text("Centimeters") },
                    onClick = {
                        iExpanded = false
                        inputUnit = "Centimeters"
                        conversionFactor.value = 0.01
                        convertUnits()
                    })

                DropdownMenuItem(text = { Text("Meters") },
                    onClick = {
                        iExpanded = false
                        inputUnit = "Meters"
                        conversionFactor.value = 1.0
                        convertUnits()
                    })

                DropdownMenuItem(text = { Text("Feet") },
                    onClick = {
                        iExpanded = false
                        inputUnit = "Feet"
                        conversionFactor.value = 0.3048
                        convertUnits()
                    })
            }
        }
            Spacer(modifier = Modifier.width(16.dp))
            //Output box
        Box{
            // Output button
            Button(onClick = { oExpanded = true }) {
                Text(text = outputUnit)
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
            }
            //Dropdown menu for right side, has menu item for each individual conversion
            DropdownMenu(expanded = oExpanded,
                onDismissRequest = { oExpanded = false }) {
                DropdownMenuItem(text = { Text("Millimeters") },
                onClick = {
                    oExpanded = false
                    outputUnit = "Millimeters"
                    conversionOutputFactor.value = 0.001
                    convertUnits()
                })

                DropdownMenuItem(text = { Text("Centimeters") },
                    onClick = {
                        oExpanded = false
                        outputUnit = "Centimeters"
                        conversionOutputFactor.value = 0.01
                        convertUnits()
                    })

                DropdownMenuItem(text = { Text("Meters") },
                    onClick = {
                        oExpanded = false
                        outputUnit = "Meters"
                        conversionOutputFactor.value = 1.0
                        convertUnits()
                    })

                DropdownMenuItem(text = { Text("Feet") },
                    onClick = {
                        oExpanded = false
                        outputUnit = "Feet"
                        conversionOutputFactor.value = 0.3048
                        convertUnits()
                    })
            }
        }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $userValue $inputUnit $outputValue is $outputUnit",
           style = MaterialTheme.typography.headlineSmall)
    }
}
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
        UnitConverter()

}
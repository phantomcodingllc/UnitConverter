package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.unitconverter.ui.theme.UnitConverterTheme

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
    Column {
        // Elements will be stacked below each other
        
        Text("Unit Converter")
        OutlinedTextField(value = "Enter value", onValueChange ={
            // This is what should happen
            // when the value of OutlinedTextField changes
        })

        Row {

            val context = LocalContext.current
            // Elements will be stacked next to each other
            Button(onClick = { Toast.makeText(context, "Thanks for clicking!",
                Toast.LENGTH_LONG).show() }) {
                Text("Click to convert")
            }
        }
        Text("Result:")
    }
}




@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
        UnitConverter()

}
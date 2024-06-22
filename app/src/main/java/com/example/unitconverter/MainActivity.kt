package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                    ){
                    UnitConverter()
                }
            }
        }
    }
}


@Composable
fun UnitConverter() {

    var inputvalue by remember { mutableStateOf("") }
    var outputValue: String by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("centimeters") }
    var outputUnit: String by remember { mutableStateOf("meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember {mutableStateOf(false)}
    var conversionFactor = remember { mutableStateOf(1.00)}
    var oconversionFactor = remember { mutableStateOf(1.00)}
    fun convertUnits(){
        val inputValueDouble = inputvalue.toDoubleOrNull() ?: 0.0
        val Result = (inputValueDouble * conversionFactor.value*100.0/oconversionFactor.value).roundToInt()/100.00
        outputValue= Result.toString()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Text(text = "           ")
        Text(text = "             ")
        Text(text = "UNIT CONVERTER", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputvalue,
            onValueChange = { inputvalue = it
        },
            label= {Text(text ="Enter Field")})

        Row {
            //input Box
            Box {
                //input button
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit   )
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded =iExpanded, onDismissRequest = { iExpanded=false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        iExpanded=false
                        inputUnit="Centimeters"
                        conversionFactor.value=0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        iExpanded=false
                        inputUnit="Meters"
                        conversionFactor.value=1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "feet") }, onClick = {
                        iExpanded=false
                        inputUnit="feet"
                        conversionFactor.value=0.3048
                        convertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            //Output Box
            Box {
                //output button
                Button(onClick = { oExpanded=true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")

                }
                DropdownMenu(expanded =oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") },
                        onClick = {
                        oExpanded=false
                            outputUnit="Centimeters"
                            oconversionFactor.value=0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        oExpanded=false
                        outputUnit="Meters"
                        oconversionFactor.value=1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "feet") }, onClick = {
                        oExpanded=false
                        outputUnit="feet"
                        oconversionFactor.value=0.3048
                        convertUnits()
                    })
                }
            }


        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $outputValue $inputvalue",
        style=MaterialTheme.typography.headlineMedium)
    }
    /*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}*/

    /*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverterTheme {
       // Greeting("Android")
    }
}*/

    @Preview(showBackground = true)
    @Composable
    fun UnitConverterPreview() {
        UnitConverter()


}}
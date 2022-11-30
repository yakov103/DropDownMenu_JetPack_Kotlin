package com.example.dropdownmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.dropdownmenu.ui.theme.DropDownMenuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DropDownMenuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var list = listOf<String>( "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10")
    dropDownMenu(list = list)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DropDownMenuTheme {
        Greeting("Android")
    }
}

@Composable
fun dropDownMenu(list : List<String>) {

    var expended by remember {
        mutableStateOf(false)
    }
    var selected by remember {
        mutableStateOf("")
    }
    var textFiledSize by remember {
        mutableStateOf(Size.Zero)
    }

    val icon = if (expended) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selected,
            onValueChange = {
                selected = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFiledSize = coordinates.size.toSize()
                },
            label = { Text(text = "Select Item") },
            trailingIcon = {
                Icon(icon, "", Modifier.clickable { expended = !expended })
            }
        )

        DropdownMenu(
            expanded = expended, onDismissRequest = { expended = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFiledSize.width.toDp() })
        ) {
            list.forEach{label ->
                DropdownMenuItem(onClick = {
                    selected = label
                    expended = false
                }) {
                    Text(text = label)
                }
            }
        }
    }



}
package com.example.andleatask.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.mvrx.compose.collectAsState
import com.example.andleatask.data.model.Event
import com.example.andleatask.ui.viewmodel.ListScreenState
import com.example.andleatask.ui.viewmodel.ListScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen(viewModel: ListScreenViewModel) {
    val listEvents by viewModel.collectAsState(ListScreenState::listEvents)
    val listSearchedEvents by viewModel.collectAsState(ListScreenState::listSearchedEvents)
    val city by viewModel.collectAsState(ListScreenState::city)
    val price by viewModel.collectAsState(ListScreenState::price)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        stickyHeader {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.width(300.dp)) {
                        NDTextField(
                            text = city,
                            modifier = Modifier.padding(16.dp),
                            onValueChange = {
                                viewModel.updateCity(city = it)
                                viewModel.searchEvents(
                                    text = it,
                                    listEvents = listEvents,
                                    price = price
                                )
                            },
                            hint = "Enter City"
                        )
                        NDTextField(
                            text = price,
                            modifier = Modifier.padding(16.dp),
                            onValueChange = {
                                viewModel.updatePrice(price = it)
                                viewModel.searchEvents(
                                    price = it,
                                    listEvents = listEvents,
                                    text = city
                                )
                            },
                            hint = "Enter Price"
                        )
                    }
                    Text(text = "Done", modifier = Modifier.padding(16.dp))
                }
            }
        }
        items(listSearchedEvents) { event ->
            ShowEvent(event = event)
        }
    }
}

@Composable
fun NDTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    hint: String,
    fontSize: TextUnit = 16.sp
) {
    TextField(
        textStyle = TextStyle(
            fontSize = fontSize,
        ),
        value = text,
        onValueChange = onValueChange,
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            cursorColor = Color.Black,
            leadingIconColor = Color.Black,
            trailingIconColor = Color.Black,
            backgroundColor = Color(0xFFEFEFEF),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(text = hint, style = TextStyle(fontSize = 16.sp, color = Color.Gray))
        }
    )
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun ShowEvent(event: Event) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "city: ${event.city ?: ""}")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "artist: ${event.name ?: ""}")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "price : ${event.price ?: ""}")
        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}

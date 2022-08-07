package com.example.andleatask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.viewModel
import com.example.andleatask.ui.screens.ListScreen
import com.example.andleatask.ui.theme.AndleaTaskTheme
import com.example.andleatask.ui.viewmodel.ListScreenViewModel

class MainActivity : ComponentActivity(), MavericksView {

    val viewModel: ListScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndleaTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ListScreen(
                        viewModel = viewModel
                    )
                }
            }
        }
    }

    override fun invalidate() {

    }
}

//@Composable
//fun ListScreen(){
//    LazyColumn(){
//        items(){
//
//        }
//    }
//}
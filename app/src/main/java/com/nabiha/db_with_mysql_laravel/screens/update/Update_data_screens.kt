package com.nabiha.db_with_mysql_laravel.screens.update

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nabiha.db_with_mysql_laravel.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Update_data_screens(
    navController: NavHostController,
    id: String?,
    updateDataModel: Update_data_model = viewModel(
        factory = UpdateDataFactory(id!!)
    )
) {
    val name = updateDataModel.data.value.name
    val context = LocalContext.current

    if (name!=""){
        var text by remember {
            mutableStateOf(name)
        }
        val coroutine = rememberCoroutineScope()

        Scaffold(
            topBar = { TopBar(navController) }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it), color = Color.White
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                    ) {

                        Text(
                            text = "Update Data",
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Button(onClick = {
                            coroutine.launch {
                                updateDataModel.updateData(text,context,navController)
                            }
                        }, modifier = Modifier.padding(bottom = 42.dp)) {
                            Text(text = "Update")
                        }

                    }
                }
            }
        }
    }




}

@Composable
fun TopBar(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 24.dp, end = 12.dp)
                    .padding(vertical = 16.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )

            Text(text = "Back")
        }
    }
}

